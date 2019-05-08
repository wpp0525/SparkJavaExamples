package jvm;

import java.util.List;

public class GenricType {

    public static void method(List<String> list) {
        System.out.println("invoke method(List<String> list)");
    }
// 不能成功运行，因为 List 会在编译其实进行泛型擦除，导致是同一个方法。
//    public static void method(List<Integer> list) {
//        System.out.println("invoke method(List<Integer> list)");
//    }
}