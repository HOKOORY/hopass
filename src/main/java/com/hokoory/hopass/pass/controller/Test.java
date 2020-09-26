package com.hokoory.hopass.pass.controller;

import com.hokoory.hopass.pass.entity.Response;
import com.hokoory.hopass.pass.entity.User;
import com.hokoory.hopass.pass.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


@RestController
public class Test{
    @Autowired
    UserMapper userMapper;
    @Autowired
    RedisTemplate<String, Serializable> redisTemplate;
    @Autowired
    RedisTemplate<String, Object> objectRedisTemplate;

    @RequestMapping(value = "/test")
    public Object test() {

//        int x = 71;
//        int[] arr = {1,2,3,4};
//        for(int i = 0;i<arr.length;i++){
//            if(x == arr[i]){
//                return i;
//            }
//        }
//        return -1;


//        int sum=0;
//        int n[] = {1,2,3,4,5};
//        System.out.println(n.length);
//        for(int i=1;i<n.length;i++){
//            sum = sum+n[i];
//        }
//        System.out.println("sum="+sum);
//        return null;

//        String name = "";
//        String password = "";
//        try{
//            List<User> list = findBySQL("select * from user where phone = ? or username = ?",name,name);
//            if (list.size() > 0){
//                for (User user : list) {
//                    if (user.getpassword == md5(password)){
//                        return 1;
//                    }
//                }
//                return -2;
//            }else {
//                return -1;
//            }
//        }catch (Exception e){
//          return  -3;
//        }




        Map<String, String> map = new HashMap<>();
        map.put("id", "17");
        User user = userMapper.getUserInPass(map);
        return new Response<>(user);

//        String key = "user:1";
//        User user = userMapper.selectOne(new QueryWrapper<User>().eq("user_name", "admin"));
//        objectRedisTempl ate.opsForValue().set(key, user);
//        User user1 = (User) objectRedisTemplate.opsForValue().get(key);
//        return XORUtils.decrypt(user1.getKeygen().getBytes(), (user1.getId() + user1.getUserName()).getBytes());
//        User user = userMapper.selectOne(new QueryWrapper<User>().eq("user_name","admin"));
    }
}
