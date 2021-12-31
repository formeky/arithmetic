package com.example.arithmetic.utils;

import java.io.File;
import java.io.FileWriter;
import java.util.HashSet;
import java.util.Random;
import java.util.Stack;

/**
 * @author zcw
 */
public class RandomFormula {

    private static Integer numberTotal = 2;
    private static Integer formulaTotal = 10;
    private static Integer numberRange = 100;
    private static Integer maxResult = 100;
    private static Boolean includeMulAndDiv = false;
    private static Boolean includeNegNum = false;


    public static void InitRandomFormula(int numberTotal, int formulaTotal, int numberRange, int maxResult, boolean includeMulAndDiv,
                         boolean includeNegNum) {
        RandomFormula.numberTotal = numberTotal;
        RandomFormula.formulaTotal = formulaTotal;
        RandomFormula.numberRange = numberRange;
        RandomFormula.maxResult = maxResult;
        RandomFormula.includeMulAndDiv = includeMulAndDiv;
        RandomFormula.includeNegNum = includeNegNum;
    }

    /**
     * 获取随机数
     *
     * @return 返回一个指定范围内的数字
     */
    public static int getRandomNumber() {
        Random rand = new Random();
        if (includeNegNum) {
            return (rand.nextInt(numberRange) + 1) * (rand.nextDouble() > 0.5 ? 1 : -1);
        } else {
            return rand.nextInt(numberRange) + 1;
        }
    }
    /**
     * 得到一个随机的运算符
     *
     * @return返回运算符
     */
    public static String getRandomOperator() {
        Random rand = new Random();
        String[] operations = { "+", "-", "*", "/" };
        return operations[rand.nextInt((includeMulAndDiv == true) ? 4 : 2)];
    }

    /**
     * 生成算式
     *
     * @return 返回算式
     */
    public static String generateFormula() {
        String formula = "";
        for (int i = 0; i < numberTotal; i++) {
            if (i >= numberTotal - 1) {
                formula += isNegNum(getRandomNumber());
                continue;
            }
            formula += isNegNum(getRandomNumber()) + " " + getRandomOperator() + " ";
        }
        return formula;
    }
    /**
     * 生成算式集合
     *
     * @return
     */
    public static HashSet<String> generateFormulas() {
        HashSet<String> set = new HashSet<String>();
        while (set.size() <= formulaTotal) {
            String formula= generateFormula();
            if(maxResult>= generateAnswer(formula)) {
                set.add(formula);
            }
        }
        return set;
    }
    /**
     * 若负数，加括号
     *
     * @param num
     * @return
     */
    public static String isNegNum(int num) {
        if (num < 0) {
            return "(" + num + ")";
        }
        return "" + num;
    }

    /**
     * 生成算式结果
     *
     * @param formula
     * @return
     */
    public static int generateAnswer(String formula) {
        int length = 0;
        String[] formulaArr = formula.split(" ");
        String operators = "+-*/";
        Stack<Integer> opNumbers = new Stack<Integer>();
        Stack<String> opOperators = new Stack<String>();
        opOperators.add("#");//字符栈中存储个#号，防止栈空
        while (length < formulaArr.length) {
            String op = formulaArr[length++];
            if (operators.indexOf(op) > -1) {// 若是运算符,判断优先级
                String sign = opOperators.peek();
                int priority = compare(op, sign);// 要入栈的跟栈顶的相比
                if (priority >= 0) {// 如果要入栈的运算符高或者相等,出栈两个数字,和之前的运算符,计算后,将数字入栈,将字符入栈
                    opNumbers.add(compute(opOperators, opNumbers));
                    opOperators.add(op);
                } else {// 入栈运算符优先级低，直接入栈
                    opOperators.add(op);
                }
                continue;
            }
            // 若是数字,则入栈
            opNumbers.add(Integer.parseInt(op.replace("(", "").replace(")", "")));
        }
        while (opOperators.peek() != "#") {
            opNumbers.add(compute(opOperators, opNumbers));
        }
        return opNumbers.pop();
    }
    /**
     * 比较运算优先级
     *
     * @return
     */
    public static int compare(String operator1, String operator2) {
        int res = 0;
        switch (operator1) {
            case "+":
            case "-":
                if ("+".equals(operator2) || "-".equals(operator2) || "*".equals(operator2) || "/".equals(operator2)) {
                    res = 1;
                } else {
                    res = -1;
                }
                break;
            case "*":
            case "/":
                if ("*".equals(operator2) || "/".equals(operator2)) {
                    res = 1;
                } else {
                    res = -1;
                }
                break;
        }
        return res;
    }
    /**
     * 算式求值
     *
     * @return
     */
    public static int compute(Stack<String> opOperators, Stack<Integer> opNumbers) {
        int num2 = opNumbers.pop();
        int num1 = opNumbers.pop();
        String _op = opOperators.pop();
        int result = 0;
        switch (_op) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                result = num1 / num2;
                break;
        }
        return result;
    }

    /**
     * 生成算式结果数组
     *
     * @param set
     * @return
     */
    public static int[] generateAnswers(HashSet<String> set) {
        int[] arr = new int[set.size()];
        int i = 0;
        for (String str : set) {
            arr[i++] = generateAnswer(str);
        }
        return arr;
    }

    /** 输出算式到文件
     * @param set
     * @return
     */
    public static String outputFormulas(HashSet<String> set) {
        File file=new File("result.txt");
        try {
            FileWriter fw = new FileWriter(file);
            for (String str : set) {
                fw.write(str + "\n");
            }
            fw.close();
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
            System.exit(0);
        }
        return file.getAbsolutePath();
    }

    /** 输出答案到文件
     * @param arr
     * @return
     */
    public static String outputAnswers(int[] arr) {
        File file=new File("answer.txt");
        try {
            FileWriter fw = new FileWriter(file);
            for (int i = 0; i < arr.length; i++) {
                fw.write(arr[i]+"\n");
            }
            fw.close();
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
            System.exit(0);
        }
        return file.getAbsolutePath();
    }
}
