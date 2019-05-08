package jvm;

import java.util.concurrent.Callable;

/**
 *  编译 javac StackTest.java
 *  反编译代码 javap -c StackTest
 *
 *  -verbose
 * */
public class StackTest {

    public  static final  int i ;
    public  final  int j;

    static {
        i = 1;
    }
    //非静态代码块
    {
        j = 2;
        System.out.println(j);
    }

    public static void main(String[] args) {
        calc();
        calc2();
//        Callable
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;

//        Integer e = 321;
//        Integer f = 321;
        Integer e = 128;
        Integer f = 128;
        Long g = 3L;
        System.out.println(c == d);
        System.out.println(e == f);  //如果是 127则为true，大数不一样
        System.out.println(c == (a + b));
        System.out.println(c.equals(a + b));
        System.out.println(g == (a + b));
        System.out.println(g.equals(a + b));

        System.out.println(i +",");
//        System.out.println();
//        System.gc();
//         test();
    }

//    public static void  test(){
//        System.out.println(j);
//    }

    public static   int calc(){
        int a = 100;
        int b = 200;
        int c = 300;
        return  (a+b )*c;

    }

    public static   int calc2(){
        int a = 1;
        int b = 2;
        int c = 3;
        int d = 4;
        d = a+b;
        return  d;
    }



}
