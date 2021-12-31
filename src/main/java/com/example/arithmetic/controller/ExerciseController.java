package com.example.arithmetic.controller;

import com.example.arithmetic.pojo.entity.QueGroup;
import com.example.arithmetic.pojo.entity.Question;
import com.example.arithmetic.pojo.enums.StatusEnum;
import com.example.arithmetic.pojo.vo.BaseVo;
import com.example.arithmetic.pojo.vo.ExerciseVo;
import com.example.arithmetic.services.ProblemService;
import com.example.arithmetic.utils.RandomFormula;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zcw
 */
@RestController
public class ExerciseController {

    @Autowired
    private ProblemService problemService;

    /**
     * @param id
     * @return
     */
    @DeleteMapping("/exercise/{id}")
    public Integer delete(@PathVariable String id){
        problemService.delete(Integer.valueOf(id));
        return StatusEnum.SUCCESS.getCode();
    }

    @PostMapping("/exercise")
    public BaseVo add(@RequestBody ExerciseVo vo){
        HashSet<String> set = RandomFormula.generateFormulas();
        int[] ints = RandomFormula.generateAnswers(set);
        int i = 0;
        List<Question> list = new ArrayList<>();
        for (String str : set) {
            Question question = new Question();
            question.setQuestion(str);
            question.setAnswer((double) ints[i++]);
            list.add(question);
        }
        problemService.insertProblem(list,vo.getLevel(),vo.getTotal());
        return new BaseVo(StatusEnum.SUCCESS.getCode(),
                "添加成功" );
    }

    @GetMapping("exercises")
    public List<QueGroup> listGroup(){
        return problemService.listGroup();
    }

    @GetMapping("problemList/{id}")
    public BaseVo listQuestion(@PathVariable Integer id){
        return new BaseVo<>(StatusEnum.SUCCESS.getCode(),problemService.listQuestion(id),"题目数据");
    }


}
