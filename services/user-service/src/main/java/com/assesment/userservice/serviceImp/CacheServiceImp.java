package com.assesment.userservice.serviceImp;

import com.assesment.userservice.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class CacheServiceImp implements CacheService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Override
    public Object getDataByKey(String key) {
        Object data = redisTemplate.opsForValue().get(key); // Retrieve data from cache
        return data;
    }

    @Override
    public Object setData(String key,Object data) {
        return null;
    }
}
