package com.example.arithmetic.services.impl;

import com.example.arithmetic.mapper.QueGroupMapper;
import com.example.arithmetic.mapper.UserMapper;
import com.example.arithmetic.pojo.entity.QueGroup;
import com.example.arithmetic.pojo.vo.RankItemVo;
import com.example.arithmetic.services.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zcw
 */
@Service
public class SystemServiceImpl implements SystemService {

    @Autowired
    private QueGroupMapper queGroupMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<RankItemVo> rank() {
        List<RankItemVo> list = queGroupMapper.rank();
        list.forEach(rankItemVo -> {
            rankItemVo.setName(userMapper.selectByPrimaryKey(rankItemVo.getOwnerId()).getUsername());
            rankItemVo.setRank(list.indexOf(rankItemVo));
        });
        return list;
    }
}
