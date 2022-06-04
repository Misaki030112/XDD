package com.hznu.xdd.util;


import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
public class HotSearchUtil {

    
    private final RedisTemplate redisTemplate;

    public HotSearchUtil(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public static long NumberOfDaysEndUnixTime(int NumberOfDays) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)-NumberOfDays,23,59,59);
        long yesterdayEnd = calendar.getTimeInMillis();
        return yesterdayEnd;
    }

    /**
     * 用于保存热搜的数据
     * @param key_name 集合的名字
     * @param key_value 集合中键的值
     * @param exist_day 存在时间
     */
    public void saveToRedis(String key_name,String key_value,Integer exist_day){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR,1);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.MILLISECOND,0);
        long timeOut = (calendar.getTimeInMillis()-NumberOfDaysEndUnixTime(exist_day)) / 1000;
        redisTemplate.expire(key_name,timeOut, TimeUnit.SECONDS);
        redisTemplate.opsForZSet().incrementScore(key_name,key_value,1);
        
    }

    /**
     * 从redis中根据集合名字获取排名前几的热搜
     * @param key_name 集合名字
     * @param size 大小
     * @return
     */
    public List<Object> getHotByRedis(String key_name,Integer size){
        List<Object> list = new ArrayList<>();
        Set<ZSetOperations.TypedTuple<Object>> typedTupleSet = redisTemplate.opsForZSet().reverseRangeWithScores(key_name,0,size - 1);
        Iterator<ZSetOperations.TypedTuple<Object>> iterator = typedTupleSet.iterator();
        int flag = 0;
        while (iterator.hasNext()){
            flag++;
            ZSetOperations.TypedTuple<Object> typedTuple = iterator.next();
            list.add(typedTuple.getValue());
            if ( flag >= size ) break;
        }
        return list;
    }
}
