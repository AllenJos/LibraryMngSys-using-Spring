package com.spring.libraryMngSys.repository;

import com.spring.libraryMngSys.model.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class CacheRepository {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    private final String USER_KEY_PREFIX = "user::";

    /**
     * username- ashish
     * key - user::ashish
     */
    public void set(MyUser myUser){
        String key = getKey(myUser.getUsername());
        redisTemplate.opsForValue().set(key, myUser, 10, TimeUnit.MINUTES);
    }

    public MyUser get(String username){
        String key = getKey(username);
        return (MyUser)redisTemplate.opsForValue().get(key);
    }

    public String getKey(String username){
        return USER_KEY_PREFIX+username;
    }
}
