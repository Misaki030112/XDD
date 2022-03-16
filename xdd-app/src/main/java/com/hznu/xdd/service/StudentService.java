package com.hznu.xdd.service;


import com.hznu.xdd.domain.VO.QuestionVO;
import com.hznu.xdd.domain.VO.VerifyMethodVO;

import java.util.List;

public interface StudentService {

    //获取所有现在正在使用的学生认证方式
    public List<VerifyMethodVO> getAllVerifyMethods();

    //获取现在正在使用的所有的问题以及答案
    public List<QuestionVO> getAllVerifyQuestions();


}
