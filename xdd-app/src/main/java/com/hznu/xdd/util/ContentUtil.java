package com.hznu.xdd.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hznu.xdd.dao.UgcDOMapper;
import com.hznu.xdd.dao.UserDOMapper;
import com.hznu.xdd.domain.Dto.TemplateData;
import com.hznu.xdd.pojo.UserDO;
import com.hznu.xdd.pojo.ugcCommentDO;
import com.hznu.xdd.service.WxOfficialService;
import com.hznu.xdd.service.impl.WxOfficialServiceImpl;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 简析内容相关方法
 * @author EmberCCC
 */
public class ContentUtil {

    private static String WxAppId="wxa9d951513d7ca374";

    private static String WxAppSecret="d992c9c01bb01269624071b165ba99f3";

    private static String template_id = "c_eXThmZYQ1GXjpygjzRD2lBZYGOR8L6WdbB1HwO1_o";



    public static boolean ContentCheck(Authentication authentication,String Content,RestTemplate restTemplate){
        String accessToken = getAccessToken(restTemplate,1);
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

    public static boolean sendMessage(String Message, RestTemplate restTemplate, List<Integer> userId, UserDOMapper userDOMapper, Integer ugcId, Integer user_id, ugcCommentDO ugcCommentDO){
        UserDO userDO1 = userDOMapper.selectByPrimaryKey(user_id);
        for (Integer integer : userId) {
            UserDO userDO = userDOMapper.selectByPrimaryKey(integer);
            String open_id_xiaododo_official_account = userDO.getOpen_id_xiaododo_official_account();
            if (!open_id_xiaododo_official_account.isEmpty() && !integer.equals(user_id)){
                String accessToken = getAccessToken(restTemplate, 2);
                HttpHeaders headers = new HttpHeaders();
                String json = TemplateData.New().setTemplate_id(template_id).setTouser(open_id_xiaododo_official_account)
                        .add("first","有评论啦")
                        .add("keyword1",userDO1.getNickname())
                        .add("keyword2",ugcCommentDO.getCreate_time().toString())
                        .add("keyword3",Message)
                        .add("remark","快去小程序看一看吧")
                        .build();
                headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
                HttpEntity<String> request = new HttpEntity<>(json, headers);
                String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + accessToken;
                ResponseEntity<String> entity = restTemplate.postForEntity(url,
                        request, String.class);
                JSONObject jsonObject = JSON.parseObject(entity.getBody());
                JSONObject result = jsonObject.getJSONObject("errcode");
                String errcode = result.getString("errcode");
            }
        }
        return true;
    }
    private static String getAccessToken(RestTemplate restTemplate,int type){
        String url = getUrl(type);
        ResponseEntity<JSONObject> wxOpenid = restTemplate.getForEntity(url,JSONObject.class);
        //通过openid 获取用户信息
        JSONObject jsonObject = JSON.parseObject(String.valueOf(wxOpenid.getBody()));
        return jsonObject.getString("access_token");
    }

    public static String  getUrl(int type) {
        Map<String,Object> map = new HashMap<>(4);
        if (type == 1){
            map.put("appid",WxAppId);
            map.put("secret",WxAppSecret);
        }else if (type == 2){
            map.put("appid","wxa9c8c5ecfa04d4c5");
            map.put("secret","bd06722f270a50fef916b91729458eb2");
        }

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

class Message implements Serializable {
    private Object value;

    public Message(Object value) {
        this.value = value;
    }


    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
