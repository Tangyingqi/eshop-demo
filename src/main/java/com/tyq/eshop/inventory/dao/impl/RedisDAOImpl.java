package com.tyq.eshop.inventory.dao.impl;

import com.tyq.eshop.inventory.dao.RedisDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

/**
 * @author tangyingqi
 * @date 2019-09-10
 */
@Repository("redisDAO")
public class RedisDAOImpl implements RedisDAO {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key,value);
    }

    @Override
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void delete(String key) {
        redisTemplate.delete(key);
    }
}
