package com.example.arithmetic.mail;


import com.example.arithmetic.utils.logger.LogAbility;

/**
 * 邮箱接口
 * @author JirathLiu
 * @date 2021/1/31
 * @description:
 */
public interface MailSender extends LogAbility {
    boolean send(Mail mail);
}
