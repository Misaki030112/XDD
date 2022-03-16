package com.hznu.xdd.controller;


import com.hznu.xdd.domain.Result;
import com.hznu.xdd.domain.VO.QuestionVO;
import com.hznu.xdd.domain.VO.VerifyMethodVO;
import com.hznu.xdd.service.MailService;
import com.hznu.xdd.service.StudentService;
import com.hznu.xdd.service.UserService;
import com.hznu.xdd.util.UserInfoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.List;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @Autowired
    MailService mailService;

    @Autowired
    UserService userService;


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
    public Result SendVerifyEmailCode(Authentication authentication,String email){
        try {
            boolean flag = mailService.SendValidCode(email, UserInfoUtil.getWxOpenIdXiaododoMini(authentication));
            if(flag) return Result.ok(null,"提交成功");
            else return new Result(20005,"数据库操作失败");
        } catch (MessagingException e) {
            e.printStackTrace();
            return new Result(20005,"邮件发送失败");
        }
    }

    @PostMapping("/post/student/verify/email/code/verify")
    public Result VerifyStudentCode(String code,Authentication authentication){
        boolean flag = userService.verifyStudentByCode(code, UserInfoUtil.getWxOpenIdXiaododoMini(authentication));
        if(flag) return Result.ok("null","验证成功");
        else return new Result(20010,"验证失败");
    }

    @PostMapping("/post/student/verify/photo")
    public Result VerifyStudentPhoto(@RequestParam String[] photos, Authentication authentication){
        boolean flag = userService.verifyStudentByPhotos(photos, UserInfoUtil.getWxOpenIdXiaododoMini(authentication));
        if(flag) return Result.ok("null","提交成功");
        else return new Result(20005,"提交失败");
    }


}
