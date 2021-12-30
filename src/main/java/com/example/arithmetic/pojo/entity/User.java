package com.example.arithmetic.pojo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
    private Integer id;

    private String username;

    private String password;

    /**
    * 年级
    */
    private Integer grade;

    private String email;

    private String avatar;
}