package lambda;

import org.junit.Test;

import java.util.Arrays;

import static java.lang.Double.sum;

public class LambdaTest {
    public static void main(String[] args) {

//        Thread tt = new Thread( () ->System.out.println("wpp"));
//        tt.start();
//
//
//        MathOperation sum = (int a, int b) -> {
//           return a+ b;
//        };
//        System.out.println(sum(3,5));


    }


    //所需接口
    interface MathOperation {
        int operate(int a, int b);

//        int operate2(int a, int b,int c);
    }

    @Test
    public void testPlus() {
        // 不用类型声明
        MathOperation plus = (a, b) -> a + b;
        System.out.println(plus.operate(1, 2));
    }




    @Test
    public void testMulti() {
        // 用大括号及返回语句
        MathOperation multi = (int a, int b) -> {
            a++ ;
            return a * b;
        };
        System.out.println(multi.operate(3, 2));
    }
}
