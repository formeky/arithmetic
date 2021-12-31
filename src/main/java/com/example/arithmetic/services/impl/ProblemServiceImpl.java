package com.example.arithmetic.services.impl;

import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.stp.StpUtil;
import com.example.arithmetic.mapper.QueGroupMapper;
import com.example.arithmetic.mapper.QuestionMapper;
import com.example.arithmetic.pojo.entity.QueGroup;
import com.example.arithmetic.pojo.entity.Question;
import com.example.arithmetic.services.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author zcw
 */
@Service
public class ProblemServiceImpl implements ProblemService {
    @Autowired
    private QueGroupMapper queGroupMapper;
    @Autowired
    private QuestionMapper questionMapper;

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Integer delete(Integer id) {
        QueGroup group = queGroupMapper.selectByPrimaryKey(id);
        if (group.getOwnerId()!= StpUtil.getLoginIdAsInt()){
            throw new NotPermissionException("无权进行此操作！");
        }
        return queGroupMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void insertProblem(List<Question> list,Integer level,Integer total) {
        QueGroup queGroup = new QueGroup();
        queGroup.setOwnerId(StpUtil.getLoginIdAsInt());
        queGroup.setCreateTime(new Date());
        queGroup.setLevel(level);
        queGroup.setNum(total);
        queGroupMapper.insertSelective(queGroup);
        List<QueGroup> groups = queGroupMapper.listQueGroup(StpUtil.getLoginIdAsInt());
        Integer groupId = groups.get(0).getId();
        list.forEach(question -> {
            question.setGroup(groupId);
            question.setStatus(0);
        });
        questionMapper.insertList(list);

    }

    @Override
    public List<QueGroup> listGroup() {
        return queGroupMapper.listQueGroup(StpUtil.getLoginIdAsInt());
    }

    @Override
    public List<Question> listQuestion(Integer groupId) {
        return questionMapper.selectByGroup(groupId);
    }


}
