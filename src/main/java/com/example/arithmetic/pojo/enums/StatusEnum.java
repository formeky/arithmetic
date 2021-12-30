package com.example.arithmetic.pojo.enums;

/**
 * @author zcw
 * @date 2021/11/21
 * @description:
 */
public enum StatusEnum{
    SUCCESS(200),

    PARAM_ERROR(400),
    UN_LOGIN(401),
    TOKEN_ERROR(402),
    FORBIDDEN(403),
    PASSWORD_IS_NOT_STRONG(404),
    USERNAME_EXISTED(405),
    EMAIL_EXISTED(405),
    MAIL_ERROR(406),
    PASSWORD_WRONG(407),
    FILE_SIZE_ERROR(408),
    ERROR(500),
    FILE_SAVE_ERROR(500)
    ;

    public Integer code;

    StatusEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
    public static StatusEnum valueOf(int code) {
        for (StatusEnum value : StatusEnum.values()) {
            if (value.code == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("argument out of range");
    }
}
