package pers.xyj;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Scanner;

@SpringBootTest
class AccountKeeperApplicationTests {
    public boolean isPalindrome(String s) {
        StringBuffer sword = new StringBuffer();
        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            if(Character.isLetterOrDigit(ch)){
                sword.append(Character.toLowerCase(ch));
            }
        }
        try{

        }finally {
            System.out.println("12");
        }
        StringBuffer reverse = new StringBuffer(sword).reverse();
        return sword.toString().equals(reverse.toString());
    }
    @Test
    void contextLoads() {
        System.out.println(isPalindrome("race a car"));
    }

}
