package com.example.arithmetic.mapper;

import com.example.arithmetic.pojo.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author zcw
 */
@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByEmail(String email);

    User simpleInfo(Integer id);

    int updateByEmailSelective(User user);
}