package com.springboot.map.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

public class redisDao {

    @Autowired
    private RedisTemplate redisTemplate;

    public List<String> getList(String key){

        List<String> result = redisTemplate.opsForList().range(key, 0, -1);
        return result;
    }

    public Long setListR(String key, String value){

       Long result = redisTemplate.opsForList().rightPush(key, value);
       return result;
    }

    public Long setListL(String key, String value){

        Long result = redisTemplate.opsForList().leftPush(key, value);
        return result;
    }

    public String getValue(String key){

        String result = String.valueOf(redisTemplate.opsForValue().get(key));
        return result;
    }

    public void setValue(String key, String value){

        redisTemplate.opsForValue().set(key, value);
    }

    public String getHash(String key1, String key2){

        String result = String.valueOf(redisTemplate.opsForHash().get(key1, key2));
        return result;
    }

    public void setHash(String key1, String key2, String value){

        redisTemplate.opsForHash().put(key1,key2,value);
    }


}
