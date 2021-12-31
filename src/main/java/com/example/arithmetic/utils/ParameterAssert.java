package com.example.arithmetic.utils;




import com.example.arithmetic.utils.check.StringBaseUtil;
import com.example.arithmetic.utils.check.UrlCheckUtil;

import java.util.Arrays;

/**
 * @throws
 * @author JirathLiu
 * @date 2020/10/10
 * @description:
 */
public class ParameterAssert {
    public static void isTrue(Boolean a,String msg){
        if (a == null || a == false) {
            throwParamExceptionWithMsg(msg);
        }
    }
    public static void isUri(String uri,String msg){
        if (!UrlCheckUtil.isUri(uri)){
            throwParamExceptionWithMsg(msg);
        }
    }
    public static void notNull(String msg,Object ...os){
        for (Object o : os) {
            if (o==null){
                throwParamExceptionWithMsg(msg);
            }
        }
    }
    public static void notNull(Object o,String msg){
        if (o==null){
            throwParamExceptionWithMsg(msg);
        }
    }
    public static void notBlank(String s, String msg){
        if (StringBaseUtil.isStringNull(s)){
            throwParamExceptionWithMsg(msg);
        }
    }

    public static void notBlank(String msg, String ...s){
        for (String amb : s) {
            if (StringBaseUtil.isStringNull(amb)){
                throwParamExceptionWithMsg(msg);
            }
        }
    }

    public static void notAllNull(String msg, Object ...os) {
        if (Arrays.stream(os).allMatch(o -> o == null)) {
            throwParamExceptionWithMsg(msg);
        }
    }
    private static void throwParamExceptionWithMsg(String msg){
        throw new ParameterException(msg);
    }
}
