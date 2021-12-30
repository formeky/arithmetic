package com.example.arithmetic.pojo.entity;

import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Question {
    private Integer id;

    /**
    * 题目
    */
    private String question;

    /**
    * 答案
    */
    private Double answer;

    /**
    * 用户答案
    */
    private Double userAnswer;

    /**
    * 0未做，1正确，2未做
    */
    private Integer status;

    /**
    * 习题组
    */
    private Integer group;

    /**
    * 提交时间
    */
    private Date submitTime;
}