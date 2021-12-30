package com.example.arithmetic.pojo.enums;

/**
 * @author zcw
 * @date 2021/11/9
 * @description:
 */
public enum GlobalRoleEnum {
    student(0),teacher(1),admin(2);
    private int code;

    GlobalRoleEnum(int code) {
        this.code = code;
    }

    public int code() {
        return this.code;
    }

    public static GlobalRoleEnum valueOf(int code) {
        switch(code) {
            case 0:
                return student;
            case 1:
                return teacher;
            case 2:
                return admin;
            default:
                throw new IllegalArgumentException("argument out of range");
        }
    }
}
