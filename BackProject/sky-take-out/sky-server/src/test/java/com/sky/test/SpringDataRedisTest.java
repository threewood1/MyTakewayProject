package com.sky.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

//@SpringBootTest
public class SpringDataRedisTest {

    @Autowired
    public RedisTemplate redisTemplate;

    @Test
    public void testRedisTemplate() {
        System.out.println(redisTemplate);
        ValueOperations valueOperations = redisTemplate.opsForValue();
        HashOperations hashOperations = redisTemplate.opsForHash();
        ListOperations listOperations = redisTemplate.opsForList();
        SetOperations setOperations = redisTemplate.opsForSet();
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();
    }

    /*
     * 操作字符串类型数据
     * */
    @Test
    public void testString() {
        // set get setex setnx
        redisTemplate.opsForValue().set("name", "xiong");
        String city = (String) redisTemplate.opsForValue().get("city");
        System.out.println(city);
        redisTemplate.opsForValue().set("code", "1234", 1, TimeUnit.MINUTES);
        redisTemplate.opsForValue().setIfAbsent("lock", "4321");
        redisTemplate.opsForValue().setIfAbsent("lock", "5214");

    }

    /*
     * 操作哈希类型数据
     * */
    @Test
    public void testHash() {
        // hset hget hdel hkeys hvals
        HashOperations hashOperations = redisTemplate.opsForHash();

        hashOperations.put("100", "name", "Tom");
        hashOperations.put("100", "age", "20");

        String name = (String) hashOperations.get("100", "name");
        System.out.println(name);

        Set keys = hashOperations.keys("100");
        System.out.println(keys);

        List values = hashOperations.values("100");
        System.out.println(values);

        hashOperations.delete("100", "age");
    }

    /*
    * 操作列表类型的数据
    * */
    @Test
    public void testList() {
    //lpush Lrange rpop llen
        ListOperations listOperations = redisTemplate.opsForList();

        listOperations.leftPushAll("mylist", "a","b","c");
        listOperations.leftPush("mylist","d");

        List mylist = listOperations.range("mylist", 0, -1);
        System.out.println(mylist);

        listOperations.rightPop("mylist");

        Long size = listOperations.size("mylist");
        System.out.println(size);
    }


}















