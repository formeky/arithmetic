package com.example.arithmetic.pojo.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zcw
 */
@Data
@NoArgsConstructor
public class SignUpVo {
    private String name;

    private String passwd;

    /**
     * 年级
     */
    private Integer grade;

    private String email;

    private String avatar;

    private String captcha;

}
