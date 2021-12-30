package com.example.arithmetic.mapper;

import com.example.arithmetic.pojo.entity.Question;
import org.springframework.stereotype.Repository;

/**
 * @author zcw
 */
@Repository
public interface QuestionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Question record);

    int insertSelective(Question record);

    Question selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Question record);

    int updateByPrimaryKey(Question record);
}