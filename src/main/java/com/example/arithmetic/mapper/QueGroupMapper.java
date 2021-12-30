package com.example.arithmetic.mapper;

import com.example.arithmetic.pojo.entity.QueGroup;
import org.springframework.stereotype.Repository;

/**
 * @author zcw
 */
@Repository
public interface QueGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(QueGroup record);

    int insertSelective(QueGroup record);

    QueGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(QueGroup record);

    int updateByPrimaryKey(QueGroup record);
}