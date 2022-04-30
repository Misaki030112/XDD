package com.hznu.xdd.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hznu.xdd.domain.Dto.MessageDto;
import com.hznu.xdd.domain.Dto.OfficialUserInfoDto;
import com.hznu.xdd.exception.AesException;
import com.hznu.xdd.pojo.UserDO;
import com.hznu.xdd.service.UserService;
import com.hznu.xdd.service.WxOfficialService;
import com.hznu.xdd.utils.WXBizMsgCrypt;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@Service
@Data
@Slf4j
public class WxOfficialServiceImpl implements WxOfficialService {

    @Autowired
    UserService userService;

    @Value("${wxOfficial.Token}")
    private    String token;
    @Value("${wxOfficial.AppId}")
    private    String appId;
    @Value("${wxOfficial.EncodingAESKey}")
    private   String EncodingAESKey;
    @Value("${wxOfficial.APPSECRET}")
    private  String APPSECRET;

    @Autowired
    RestTemplate restTemplate;

    // https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
    private  String UserInfoUrlPrefix="https://api.weixin.qq.com/cgi-bin/user/info?";

    private WXBizMsgCrypt wxBizMsgCrypt;

    @PostConstruct
    public void init(){
        try {
            wxBizMsgCrypt=new WXBizMsgCrypt(token,EncodingAESKey,appId);
        } catch (AesException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String verifyFromWechatInit(String msgSignature, String timeStamp, String nonce, String echoStr) throws AesException {
        return wxBizMsgCrypt.verifyUrl(msgSignature, timeStamp, nonce, echoStr);
    }

    @Override
    public boolean verifyFromWechat(String msgSignature, String timeStamp, String nonce) {
        boolean flag=false;
        try {
            flag= wxBizMsgCrypt.verifyUrl(msgSignature, timeStamp, nonce);
        } catch (AesException e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public Map<String, String> analysisData(HttpServletRequest request, MessageDto msg) throws IOException, AesException, DocumentException {
        String fromXML = streamToString(request);
        String result = wxBizMsgCrypt.decryptMsg(msg.getMsg_signature(), msg.getTimestamp(), msg.getNonce(), fromXML);
        Map<String, String> map = new HashMap<>();
        //将解密后的消息转为xml
        Document doc = DocumentHelper.parseText(result);
        Element root = doc.getRootElement();
        List<Element> list = root.elements();
        for (Element e : list) {
            map.put(e.getName(), e.getText());
        }
        return map;
    }

    @Override
    public void ProcessUserAction(Map<String, String> map, HttpServletResponse response) throws AesException, IOException {
        if(map.get("MsgType").equals("event")){
            if(map.get("Event").equals("subscribe")){
                String message = MakeTextMessage(map.get("ToUserName"), map.get("FromUserName"));
                response.getWriter().print(message);
                AttentionOfficial(map,true);
            }
            if(map.get("Event").equals("unsubscribe")){
                response.getWriter().print("");
                AttentionOfficial(map,false);
            }
        }
        if(map.get("MsgType").equals("text")){

        }
    }

    @Override
    public void AttentionOfficial(Map<String, String> map,Boolean flag) {
        UserDO user=null;
        String OpenId = map.get("FromUserName");
        if(flag){
            String accessToken = getAccessToken();
            ResponseEntity<JSONObject> entity = restTemplate.getForEntity(getUserInfoUrl(OpenId,accessToken), JSONObject.class);
            log.info("还没解析的数据：{}",entity);
            OfficialUserInfoDto infoDto = Objects.requireNonNull(entity.getBody()).toJavaObject(OfficialUserInfoDto.class);
            log.info("用户信息数据为：{}",infoDto);
            assert infoDto!=null;
            user = userService.getUserByUnionId(infoDto.getUnionid());
            assert user!=null;
            user.setOpen_id_xiaododo_official_account(OpenId);
            userService.updateUser(user);
            log.info("成功绑定用户关注公众号OpenID！");

        }else{
            user = userService.getUserByOfficialWxOpenId(OpenId);
            assert user!=null;
            user.setOpen_id_xiaododo_official_account(null);
            userService.updateUser(user);
            log.info("成功取消用户关注公众号OpenID！");
        }
    }




    private String streamToString(HttpServletRequest request) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }


    private String getUserInfoUrl(String openId,String access_token){
        return UserInfoUrlPrefix+"access_token="+access_token+"&openid="+openId+"&lang=zh_CN";
    }


    private String MakeImg(String Id,String ToUserName ,String FromUserName) throws AesException {
        long millis = System.currentTimeMillis();
        String model="<xml>\n" +
                "  <ToUserName><![CDATA["+ToUserName+"]]></ToUserName>\n" +
                "  <FromUserName><![CDATA["+FromUserName+"]]></FromUserName>\n" +
                "  <CreateTime>"+millis+"</CreateTime>\n" +
                "  <MsgType><![CDATA[image]]></MsgType>\n" +
                "  <Image>\n" +
                "    <MediaId><![CDATA["+Id+"]]></MediaId>\n" +
                "  </Image>\n" +
                "</xml>";
        String msg = wxBizMsgCrypt.encryptMsg(model, String.valueOf(millis), "123456");
        return msg;
    }

    private String MakeTextMessage(String ToUserName ,String FromUserName) throws AesException {
        long millis = System.currentTimeMillis();
        String model="<xml>\n" +
                "  <ToUserName><![CDATA["+ToUserName+"]]></ToUserName>\n" +
                "  <FromUserName><![CDATA["+FromUserName+"]]></FromUserName>\n" +
                "  <CreateTime>"+millis+"</CreateTime>\n" +
                "  <MsgType><![CDATA[text]]></MsgType>\n" +
                "  <Content><![CDATA[Halo，又有一个小机灵鬼发现我们啦\n" +
                "\n" +
                "快来看看大家都在聊什么吧！\n" +
                "\n" +
                "“赶紧来看看吧！\n" +
                "”]]></Content>\n" +
                "</xml>";
        return wxBizMsgCrypt.encryptMsg(model, String.valueOf(millis),"123456");
    }

    public  static String getAccessToken(){
        RestTemplate restTemplate1 = new RestTemplate();
        ResponseEntity<JSONObject> forEntity = restTemplate1.getForEntity("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxa9c8c5ecfa04d4c5&secret=bd06722f270a50fef916b91729458eb2", JSONObject.class);
        String access_token = Objects.requireNonNull(forEntity.getBody()).getString("access_token");
        assert  access_token!=null;
        return  access_token;
    }




}
