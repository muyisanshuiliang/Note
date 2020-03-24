package com.example.demo.mybatisplus.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.mybatisplus.mapper.UserMapper;
import com.example.demo.mybatisplus.model.User;
import org.springframework.stereotype.Service;

/**
 * @Author yl
 * @Date 2019/12/16 16:03
 * @description:
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
