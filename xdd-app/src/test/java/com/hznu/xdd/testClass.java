package com.hznu.xdd;

import com.hznu.xdd.dao.UserDOMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Xdd_Application.class)
public class testClass {

    @Autowired
    UserDOMapper userDOMapper;


    @Test
    public void test01(){
        System.out.println(userDOMapper.selectByPrimaryKey(1));
    }


}
