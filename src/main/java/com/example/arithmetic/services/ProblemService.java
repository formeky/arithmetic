package com.example.arithmetic.services;

import com.example.arithmetic.pojo.entity.QueGroup;
import com.example.arithmetic.pojo.entity.Question;

import java.util.List;

/**
 * @author zcw
 */
public interface ProblemService {
    public Integer delete(Integer id);

    void insertProblem(List<Question> list,Integer level,Integer total);

    List<QueGroup> listGroup();

    List<Question> listQuestion(Integer groupId);
}
