package com.hznu.xdd.controller;


import com.hznu.xdd.domain.Result;
import com.hznu.xdd.domain.VO.QuestionVO;
import com.hznu.xdd.domain.VO.VerifyMethodVO;
import com.hznu.xdd.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;


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




}
