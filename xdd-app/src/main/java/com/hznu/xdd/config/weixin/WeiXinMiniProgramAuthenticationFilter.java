package com.hznu.xdd.config.weixin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hznu.xdd.pojo.UserDO;
import com.hznu.xdd.service.UserService;
import com.hznu.xdd.util.RedisUtil;
import com.hznu.xdd.utils.WeChatUtil;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 微信小程序登录的过滤器
 * @author Misaki
 */
@Slf4j
public class WeiXinMiniProgramAuthenticationFilter extends
        AbstractAuthenticationProcessingFilter {

    @Setter
    private UserService userService;
    @Setter
    private RestTemplate restTemplate;
    @Setter
    private RedisUtil redisUtil;


    private static final String WxAppId="wxa9d951513d7ca374";

    private static final String WxAppSecret="d992c9c01bb01269624071b165ba99f3";

    private static final String httpMethod ="POST";

    public WeiXinMiniProgramAuthenticationFilter() {
        super(new AntPathRequestMatcher("/post/user/create", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException{

        if (!request.getMethod().equals(httpMethod)) {
            throw new WeiXinMiniProgramAuthenticationException("不支持此种请求方式: " + request.getMethod());
        }

        String code = request.getParameter("code");
        String url = getUrl(code);
        ResponseEntity<String> wxOpenid = restTemplate.getForEntity(url,String.class);
        //通过openid 获取用户信息
        JSONObject jsonObject = JSON.parseObject(wxOpenid.getBody());
        UserDO user= remoteClientInvoke(jsonObject);
        WeiXinMiniProgramAuthenticationToken authRequest = new WeiXinMiniProgramAuthenticationToken(user,null);
        redisUtil.set(user.getOpen_id_xiaododo_mini(),jsonObject.getString("session_key"));

        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }


    private UserDO remoteClientInvoke(JSONObject object) {
        String openid = object.getString("openid");
        String union_id=object.getString("unionid");

        UserDO user = userService.getUserByWxOpenId(openid);
        if(user==null){
            log.info("新用户注册!"+user);
            user = new UserDO();
            user.setOpen_id_xiaododo_mini(openid).setCreate_time(new Date());
            if(userService.addUser(user)){
                log.info("新用户注册成功");
            }else throw new WeiXinMiniProgramAuthenticationException("用户注册失败");
        }
        user.setUnion_id(union_id);
        user.setCreate_time(new Date());


        return user;
    }



    protected void setDetails(HttpServletRequest request,
                              WeiXinMiniProgramAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    public static String  getUrl(String code) {
        Map<String,Object> map = new HashMap<>(4);
        map.put("appid",WxAppId);
        map.put("secret",WxAppSecret);
        map.put("js_code",code);
        map.put("grant_type","authorization_code");
        String param = "";
        Iterator<String> it = map.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            param += key + "=" + map.get(key) + "&";
        }
        return "https://api.weixin.qq.com/sns/jscode2session?"+param;
    }
}
