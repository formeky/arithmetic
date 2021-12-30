package com.example.arithmetic.pojo.entity;

import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QueGroup {
    private Integer id;

    /**
    * 题目数量
    */
    private Integer num;

    /**
    * 题目难度
    */
    private Integer level;

    /**
    * 生成时间
    */
    private Date createTime;

    /**
    * 完成时间
    */
    private Date endTime;

    /**
    * 用户id
    */
    private Integer ownerId;
}