package com.example.arithmetic.utils;

import java.util.Random;

/**
 * @author zcw
 */
public class CaptchaUtil {
    public static String getRandomNumCode(int number){
        String codeNum = "";
        int [] numbers = {0,1,2,3,4,5,6,7,8,9};
        Random random = new Random();
        for (int i = 0; i < number; i++) {
            int next = random.nextInt(10000);//目的是产生足够随机的数，避免产生的数字重复率高的问题
            codeNum+=numbers[next%10];
        }

        return codeNum;
    }

    public static void main(String[] args) {
        System.out.println(CaptchaUtil.getRandomNumCode(6));
    }
}
