package com.hznu.xdd.TimeOutTask;


import com.hznu.xdd.dao.UgcDOMapper;
import com.hznu.xdd.domain.pojoExam.UgcDOExample;
import com.hznu.xdd.pojo.UgcDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.hznu.xdd.service.impl.UGCServiceImpl.NumberOfDaysEndUnixTime;

@Component
public class HotUGCTimeTask {

    @Autowired
    UgcDOMapper ugcDOMapper;

    @Autowired
    RedisTemplate redisTemplate;
    @Scheduled(cron = "* */10 * * * ?")
    private void saveHotUGC(){
        UgcDOExample ugcDOExample = new UgcDOExample();
        List<UgcDO> ugcDOS = ugcDOMapper.selectByExample(ugcDOExample);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR,1);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.MILLISECOND,0);
        //一天清空
        long timeOut = (calendar.getTimeInMillis()-NumberOfDaysEndUnixTime(1)) / 1000;
        ugcDOS.forEach((item)->{
            redisTemplate.expire("HotUGC",timeOut, TimeUnit.SECONDS);
            redisTemplate.opsForZSet().add("HotUGC",item.getId(),item.getExposure() * 0.2 + item.getVote() * 0.3 + item.getComment() * 0.5);
        });

//        System.out.println(size);
    }
}
