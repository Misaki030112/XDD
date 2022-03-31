package com.hznu.xdd.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hznu.xdd.pojo.UserDO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 简析内容相关方法
 * @author EmberCCC
 */
public class ContentUtil {

    private static String WxAppId="wxa9d951513d7ca374";

    private static String WxAppSecret="d992c9c01bb01269624071b165ba99f3";


    public static boolean ContentCheck(Authentication authentication,String Content,RestTemplate restTemplate){
        String accessToken = getAccessToken(restTemplate);
        UserDO userDO = (UserDO)authentication.getPrincipal();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        Map<String, Object> map = new HashMap<>(16);
        map.put("version", 2);
        map.put("openid", userDO.getOpen_id_xiaododo_mini());
        map.put("scene", 2);
        map.put("content", Content);

        HttpEntity<Map<String,Object>> request = new HttpEntity<Map<String,Object>>(map, headers);
        String url = "https://api.weixin.qq.com/wxa/msg_sec_check?access_token=" + accessToken;
        ResponseEntity<String> entity = restTemplate.postForEntity(url,
                request, String.class);

        JSONObject jsonObject = JSON.parseObject(entity.getBody());

        JSONObject result = jsonObject.getJSONObject("result");
        String label = result.getString("label");
        return label.equals("100");
    }

    private static String getAccessToken(RestTemplate restTemplate){
        String url = getUrl();
        ResponseEntity<String> wxOpenid = restTemplate.getForEntity(url,String.class);
        //通过openid 获取用户信息
        JSONObject jsonObject = JSON.parseObject(wxOpenid.getBody());
        return jsonObject.getString("access_token");
    }

    public static String  getUrl() {
        Map<String,Object> map = new HashMap<>(4);
        map.put("appid",WxAppId);
        map.put("secret",WxAppSecret);
        map.put("grant_type","client_credential");
        String param = "";
        Iterator<String> it = map.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            param += key + "=" + map.get(key) + "&";
        }
        param = param.substring(0,param.length() - 1);
        return "https://api.weixin.qq.com/cgi-bin/token?"+param;
    }

}
