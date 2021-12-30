package com.example.arithmetic.pojo.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserVo {
    private Integer id;

    private String username;

    private String passwd;

    /**
     * 年级
     */
    private Integer grade;

    private String email;

    private String avatar;
}
