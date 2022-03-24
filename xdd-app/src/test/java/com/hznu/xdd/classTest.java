package com.hznu.xdd;

import com.hznu.xdd.dao.UgcDOMapper;
import com.hznu.xdd.dao.UserDOMapper;
import com.hznu.xdd.domain.Dto.attachmentDto;
import com.hznu.xdd.domain.VO.AnswerVO;
import com.hznu.xdd.domain.VO.QuestionVO;
import com.hznu.xdd.domain.Dto.UGCDto;
import com.hznu.xdd.domain.VO.UGCVO;
import com.hznu.xdd.domain.pojoExam.UgcDOExample;
import com.hznu.xdd.pojo.UgcDO;
import com.hznu.xdd.pojo.UserDO;
import com.hznu.xdd.pojo.answerDO;
import com.hznu.xdd.domain.pojoExam.answerDOExample;
import com.hznu.xdd.pojo.questionDO;
import com.hznu.xdd.service.UGCService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Xdd_Application.class)
public class classTest {

}
