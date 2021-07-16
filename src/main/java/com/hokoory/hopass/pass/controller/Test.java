package com.hokoory.hopass.pass.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hokoory.hopass.pass.entity.Response;
import com.hokoory.hopass.pass.entity.TestMongoDB;
import com.hokoory.hopass.pass.entity.User;
import com.hokoory.hopass.pass.mapper.UserMapper;
import com.hokoory.hopass.pass.service.IAsyncService;
import com.hokoory.hopass.utils.XORUtils;
import jdk.swing.interop.SwingInterOpUtils;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@RestController
public class Test {
    @Autowired
    UserMapper userMapper;
    @Autowired
    RedisTemplate<String, Serializable> redisTemplate;
    @Autowired
    RedisTemplate<String, Object> objectRedisTemplate;
//    @Autowired
//    private Queue queue;
//    @Autowired
//    private Topic topic;
//    @Autowired
//    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private IAsyncService asyncService;

    @RequestMapping(value = "/test")
    public Object test(@RequestParam(value = "id", required = false, defaultValue = "1") int id) {
        /*
        //测试一对多
        Map<String, String> map = new HashMap<>();
        map.put("id", "17");
        User user = userMapper.getUserInPass(map);
        return new Response<>(user);
        */


        //测试redis
        //测试 XOR加解密
//        String key = "user:17";
//        Map<String, String> map = new HashMap<>();
//        map.put("user_name", "admin");
//        User user = userMapper.getUserByUsername(map);
//        objectRedisTemplate.opsForValue().set(key, user);
//        User user1 = (User) objectRedisTemplate.opsForValue().get(key);
//        String XORDecode = new String(XORUtils.decrypt(user1.getKeygen().getBytes(), (user1.getId() + user1.getUserName()).getBytes()));
//        return XORDecode;


//        TestMongoDB testMongoDB = new TestMongoDB(id + "", "test" + id + id);
//        mongoTemplate.save(testMongoDB);
//        TestMongoDB res = mongoTemplate.findById(id + "", TestMongoDB.class);
//        List<TestMongoDB> list = mongoTemplate.findAll(TestMongoDB.class);
        for (int ii = 0; ii <= 10; ii++) {
            asyncService.executorAsync();
//            System.out.println("afs");
        }
        return 1;
    }
}
