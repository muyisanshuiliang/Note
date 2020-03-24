package com.example.demo;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.mybatisplus.mapper.UserMapper;
import com.example.demo.mybatisplus.model.User;
import com.example.demo.mybatisplus.service.UserService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DecimalFormat;
import java.util.List;

/**
 * @Author yl
 * @Date 2019/12/16 14:17
 * @description:
 */
@SpringBootTest
public class MybatisPlusTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Test
    void testInsert() {
        User user = new User();
        user.setAddress("四川省成都市");
        user.setName("张三1");
        user.setNumber(2019002);
        userMapper.insert(user);
    }

    @Test
    void batchTest() {
        List<User> users = Lists.newArrayList();
        User user;
        for (int i = 0; i < 100; i++) {
            user = new User();
            String format = new DecimalFormat("00000").format(i);
            user.setAddress("四川省成都市" + format);
            user.setName("张三" + format);
            user.setNumber(2019001 + i);
            users.add(user);
        }
        userService.saveBatch(users, 50);
    }

    @Test
    void testInsert1() {
        User user = new User();
        user.setAddress("四川省成都市");
        user.setName("张三1");
        user.setNumber(2019002);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("father", "zhangsan's father");
        jsonObject.put("mother", "zhangsan's mother");
        jsonObject.put("son", "zhangsan's son");
        user.setDetailInfo(jsonObject);
        userMapper.insert(user);
    }


    @Test
    void testUpdate1() {
        User user = new User();
        user.setAddress("四川省成都市1111");
        user.setName("张三3");
        user.setId(2);
        user.setNumber(2019001);
        userMapper.updateById(user);
    }

    @Test
    void testUpdate2() {
        User user = new User();
        user.setId(2);
        user.setNumber(2019002);
        userMapper.updateById(user);
    }

    @Test
    void get() {
        User user = userMapper.selectById(3);
        System.out.println(user);
    }

    @Test
    void deleteAll() {
        int i = userMapper.deleteAll();
        System.out.println("影响行数：" + i);
    }
}
