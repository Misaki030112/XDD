package com.hznu.xdd;

import com.hznu.xdd.dao.answerDOMapper;
import com.hznu.xdd.domain.VO.AnswerVO;
import com.hznu.xdd.domain.VO.QuestionVO;
import com.hznu.xdd.pojo.answerDO;
import com.hznu.xdd.pojo.answerDOExample;
import com.hznu.xdd.pojo.questionDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com.hznu.xdd.dao.answerDOMapper")
public class classTest {


    @Autowired
    com.hznu.xdd.dao.answerDOMapper answerDOMapper;

    @Test
    public void test(){
        questionDO questionDO = new questionDO();
        questionDO.setId(1);

        questionDO questionDO1 = new questionDO();
        questionDO1.setId(2);

        answerDO answerDO = new answerDO();
        answerDO.setId(1);

        ArrayList<questionDO> arrayList = new ArrayList<>();
        arrayList.add(questionDO);
        arrayList.add(questionDO1);

        ArrayList<QuestionVO> objects = new ArrayList<>();

        arrayList.forEach((q)->{
            QuestionVO questionVO = new QuestionVO();
            questionVO.setId(q.getId());
            questionVO.setAnswer(query(q.getId()));
            objects.add(questionVO);
        });

        System.out.println(objects);
        System.out.println(objects.size());
    }

    public List<AnswerVO> query(Integer questionId){
        answerDOExample example = new answerDOExample();
        answerDOExample.Criteria criteria = example.createCriteria();
        criteria.andQuestion_idEqualTo(questionId);
        List<answerDO> answerDOList = answerDOMapper.selectByExample(example);
        ArrayList<AnswerVO> answerVOS = new ArrayList<>();
        answerDOList.forEach((txt)->{
            AnswerVO answerVO = new AnswerVO();
            answerVO.setId(txt.getId());
            answerVO.setContent(txt.getContent());
            answerVOS.add(answerVO);
        });
        return answerVOS;
    }
}
