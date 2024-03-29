package com.hznu.xdd.service.impl;

import com.hznu.xdd.dao.answerDOMapper;
import com.hznu.xdd.dao.questionDOMapper;
import com.hznu.xdd.dao.verify_methodDOMapper;
import com.hznu.xdd.domain.VO.AnswerVO;
import com.hznu.xdd.domain.VO.QuestionVO;
import com.hznu.xdd.domain.VO.VerifyMethodVO;
import com.hznu.xdd.domain.pojoExam.answerDOExample;
import com.hznu.xdd.domain.pojoExam.questionDOExample;
import com.hznu.xdd.domain.pojoExam.verify_methodDOExample;
import com.hznu.xdd.pojo.answerDO;
import com.hznu.xdd.pojo.questionDO;
import com.hznu.xdd.pojo.verify_methodDO;
import com.hznu.xdd.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    verify_methodDOMapper verifyMethodDoMapper;
    @Autowired
    answerDOMapper answerDoMapper;
    @Autowired
    questionDOMapper questionDoMapper;


    @Override
    public List<VerifyMethodVO> getAllVerifyMethods() {
        verify_methodDOExample example = new verify_methodDOExample();
        verify_methodDOExample.Criteria criteria = example.createCriteria();
        criteria.andIs_deleteEqualTo(false);
        criteria.andIs_onlineEqualTo(true);

        List<verify_methodDO> verify_methodDO = verifyMethodDoMapper.selectByExample(example);
        List<VerifyMethodVO> verifyMethodVOS = new ArrayList<>();



        verify_methodDO.forEach((v)->{
            verifyMethodVOS.add(new VerifyMethodVO(v));
        });
        return verifyMethodVOS;
    }

    //随机选择
    private List<questionDO> createRandoms(List<questionDO> list, int n) {
        Map<Integer,String> map = new HashMap<>();
        List<questionDO> news = new ArrayList<>();
        if (list.size() <= n) {
            return list;
        } else {
            while (map.size() < n) {
                int random = (int)(Math.random() * list.size());
                if (!map.containsKey(random)) {
                    map.put(random, "");
                    news.add(list.get(random));
                }
            }
            return news;
        }
    }



    @Override
    public List<QuestionVO> getAllVerifyQuestions() {
        questionDOExample example = new questionDOExample();
        questionDOExample.Criteria criteria = example.createCriteria();
        criteria.andIs_deleteEqualTo(false);
        criteria.andIs_onlineEqualTo(true);
        List<questionDO> questionDOS = questionDoMapper.selectByExample(example);

        List<questionDO> randoms = createRandoms(questionDOS, 20);
        answerDOExample example1 = new answerDOExample();
        answerDOExample.Criteria criteria1 = example1.createCriteria();
        criteria1.andIs_deleteEqualTo(false);
        List<answerDO> answerDOS = answerDoMapper.selectByExample(example1);

        List<QuestionVO> questionVOS = new ArrayList<>();
        randoms.forEach((q)->{
            QuestionVO questionVO = new QuestionVO(q);
            List<AnswerVO> answerVOS = new ArrayList<>();
            answerDOS.stream().filter(a -> Objects.equals(a.getQuestion_id(), q.getId())).forEach((a)->{
                answerVOS.add(new AnswerVO(a));
            });
            questionVO.setAnswer(answerVOS);
            questionVOS.add(questionVO);
        });

        return questionVOS;
    }
}
