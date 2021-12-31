package com.example.arithmetic.utils;

import com.example.arithmetic.pojo.entity.User;
import com.example.arithmetic.pojo.vo.SignUpVo;
import com.example.arithmetic.pojo.vo.UserVo;
import org.springframework.beans.BeanUtils;

/**
 * @author zcw
 */
public class SignUpConvert {

    public static User voConvert(SignUpVo source){
        if (source==null){
            return null;
        }
        User amb = new User();
        BeanUtils.copyProperties(source,amb);
        amb.setPassword(source.getPasswd());
        amb.setUsername(source.getName());
        return amb;
    }

}
