package com.hznu.xdd.controller;

import com.hznu.xdd.domain.Dto.MessageDto;
import com.hznu.xdd.exception.AesException;
import com.hznu.xdd.service.WxOfficialService;
import com.hznu.xdd.service.impl.WxOfficialServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@RestController
@Slf4j
public class WxOfficialController {
    @Autowired
    WxOfficialService wxOfficialService;

    @GetMapping ("/officialAccount/wx")
    public void InitOfficialHandler(MessageDto messageDto, HttpServletResponse response) throws IOException {
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        String echostr=null;
        try {
           echostr  = wxOfficialService.verifyFromWechatInit(messageDto.getSignature(), messageDto.getTimestamp(), messageDto.getNonce(), messageDto.getEchostr());
        } catch (AesException e) {
            e.printStackTrace();

            response.getWriter().write("error");
        }
        response.getWriter().print(echostr);
    }

    @PostMapping("/officialAccount/wx")
    public void OfficialHandler(MessageDto messageDto,HttpServletRequest request,HttpServletResponse response) throws IOException {
        response.setHeader("Content-type", "text/html;charset=UTF-8");
       if(wxOfficialService.verifyFromWechat(messageDto.getSignature(),messageDto.getTimestamp(),messageDto.getNonce())){
           response.getWriter().print("");
           log.info("验证来自微信服务器成功！");
           try {
               Map<String, String> map = wxOfficialService.analysisData(request, messageDto);
               log.info("解析的参数为：{}",map);
               wxOfficialService.ProcessUserAction(map);

           } catch (AesException | DocumentException e) {
               log.info("消息解密失败！");
               e.printStackTrace();
           }
       }else{

       }
    }




}
