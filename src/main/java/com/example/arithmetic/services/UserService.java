package com.example.arithmetic.services;

import com.example.arithmetic.pojo.entity.User;
import io.swagger.models.auth.In;

/**
 * @author zcw
 */
public interface UserService {
    public User getUser(String email);

    public User userInfo(Integer id);

    public User simpleInfo(Integer id);

    public Boolean updateUser(User user);

//    public

}
