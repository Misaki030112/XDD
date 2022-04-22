package com.hznu.xdd.service.impl;

import com.hznu.xdd.domain.VO.hotSearchVO;
import com.hznu.xdd.service.OtherService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
public class OtherServiceImpl implements OtherService {
    @Resource
    RedisTemplate redisTemplate;

    public List<hotSearchVO> getHotSearch(){
        List<hotSearchVO> hotWordList = new ArrayList<>();
        Set<ZSetOperations.TypedTuple<Object>> typedTupleSet = redisTemplate.opsForZSet().reverseRangeByScoreWithScores("hotWord",1,100);
        Iterator<ZSetOperations.TypedTuple<Object>> iterator = typedTupleSet.iterator();
        int flag = 0;
        while (iterator.hasNext()){
            flag++;
            ZSetOperations.TypedTuple<Object> typedTuple = iterator.next();
            String value = (String)typedTuple.getValue();
            hotSearchVO hotWord = new hotSearchVO(flag,value);
            hotWordList.add(hotWord);
            if ( flag >= 10 ) break;
        }

        return hotWordList;
    }
}
