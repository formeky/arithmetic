package com.example.arithmetic;

import com.example.arithmetic.utils.RandomFormula;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashSet;

@SpringBootTest
class ArithmeticApplicationTests {

    @Test
    void contextLoads() {
        RandomFormula.InitRandomFormula(6,10,100,100,true,false);
        HashSet<String> set = RandomFormula.generateFormulas();
        int[] answers = RandomFormula.generateAnswers(set);
        System.out.println(set);
        System.out.println(Arrays.toString(answers));
    }

}
