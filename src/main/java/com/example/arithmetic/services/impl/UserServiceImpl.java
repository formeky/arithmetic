package com.example.arithmetic.services.impl;

import com.example.arithmetic.mapper.UserMapper;
import com.example.arithmetic.pojo.entity.User;
import com.example.arithmetic.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zcw
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUser(String email) {
        return userMapper.selectByEmail(email);
    }
}
