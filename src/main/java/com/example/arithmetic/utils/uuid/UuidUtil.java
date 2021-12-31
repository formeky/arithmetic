package com.example.arithmetic.utils.uuid;

import java.util.UUID;

/**
 * @author JirathLiu
 */
public class UuidUtil {
    public static String genUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
