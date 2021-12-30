package com.example.arithmetic.services.impl;

import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import com.example.arithmetic.mapper.UserMapper;
import com.example.arithmetic.pojo.entity.User;
import com.example.arithmetic.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public User userInfo(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public User simpleInfo(Integer id) {
        return userMapper.simpleInfo(id);
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Boolean updateUser(User user) {
        User u = userMapper.selectByEmail(user.getEmail());
        if (u==null||!u.getId().equals(StpUtil.getLoginIdAsInt())) {
            throw new NotPermissionException("用户不存在或无权访问");
        }
        user.setPassword(SaSecureUtil.md5(user.getPassword()));
        userMapper.updateByEmailSelective(user);
        return true;
    }
}
