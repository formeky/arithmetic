package com.example.arithmetic.mapper;

import com.example.arithmetic.pojo.entity.QueGroup;
import com.example.arithmetic.pojo.vo.RankItemVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    List<RankItemVo> rank();

    List<QueGroup> listQueGroup(@Param("ownerId") int loginIdAsInt);
}