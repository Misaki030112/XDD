package com.hznu.xdd.controller;


import com.hznu.xdd.domain.Dto.UserDto;
import com.hznu.xdd.domain.Result;
import com.hznu.xdd.domain.VO.Message;
import com.hznu.xdd.domain.VO.QuestionVO;
import com.hznu.xdd.domain.VO.VerifyMethodVO;
import com.hznu.xdd.service.MailService;
import com.hznu.xdd.service.StudentService;
import com.hznu.xdd.service.UserService;
import com.hznu.xdd.util.UserInfoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.List;

@RestController
@Transactional(rollbackFor = Exception.class)
public class StudentController {

    @Autowired
    StudentService studentService;

    @Autowired
    MailService mailService;

    @Autowired
    UserService userService;
    @Autowired
    UserInfoUtil userInfoUtil;


    @GetMapping("/get/student/verify")
    public Result verifyMethods(){
        List<VerifyMethodVO> allVerifyMethods = studentService.getAllVerifyMethods();
        return Result.ok(allVerifyMethods,"获取成功");
    }


    @PostMapping("/post/student/verify/question")
    public Result AllProblems(){
        List<QuestionVO> questions = studentService.getAllVerifyQuestions();
        return Result.ok(questions,"获取成功");
    }

    @PostMapping("/post/student/verify/email/code/send")
    public Result SendVerifyEmailCode(Authentication authentication,@RequestBody UserDto userDto){
        try {
            mailService.SendValidCode(userDto.getEmail(), UserInfoUtil.getWxOpenIdXiaododoMini(authentication));
            return Result.ok(null,"提交成功");
        } catch (MessagingException e) {
            e.printStackTrace();
            return new Result(20005,"邮件发送失败");
        }
    }

    @PostMapping("/post/student/verify/email/code/verify")
    public Result VerifyStudentCode(@RequestBody UserDto userDto,Authentication authentication){
        boolean flag = userService.verifyStudentByCode(userDto.getCode(), UserInfoUtil.getWxOpenIdXiaododoMini(authentication));
        if(flag) return Result.ok("null","验证成功");
        else return new Result(20010,"验证失败");
    }

    @PostMapping("/post/student/verify/photo")
    public Result VerifyStudentPhoto(@RequestBody UserDto userDto, Authentication authentication){
        boolean flag = userService.verifyStudentByPhotos(userDto.getPhotos(), UserInfoUtil.getWxOpenIdXiaododoMini(authentication));
        if(flag) return Result.ok("null","提交成功");
        else return new Result(20005,"提交失败");
    }

    @PostMapping("/post/student/phone")
    public Result bindPhone(@RequestBody UserDto userDto,Authentication authentication){
       if( userService.bindPhone(UserInfoUtil.getWxOpenIdXiaododoMini(authentication),userDto.getEncryptedData(),userDto.getIv(),userDto.getCode(),userInfoUtil.getSessionKey(authentication))){
           return Result.ok(null,"绑定成功");
       }else{
           return new Result(20004,"绑定失败");
       }
    }

    @PostMapping("/post/student/verify/status")
    public Result verifyStatus(Authentication authentication){
        int flag = userService.verifyStudent(UserInfoUtil.getWxOpenIdXiaododoMini(authentication));
        if(flag==2){
            return Result.ok(null,"账号状态修改成功");
        }else if(flag==1){
            return Result.ok(new Message("小喇叭收到，将在1-2个工作日内完成审核～"),"状态状态修改申请成功");
        }else{
            return new Result(20001,"修改失败");
        }
    }


    @PostMapping("/post/student/verify/finish/question")
    public Result Finish(Authentication authentication){
        boolean flag = userService.finishQuestion(UserInfoUtil.getWxOpenIdXiaododoMini(authentication));
        if(flag){
            return Result.ok(null,"认证方式填充成功");
        }else return new Result(20001,"认证方式填充失败");
    }



}
