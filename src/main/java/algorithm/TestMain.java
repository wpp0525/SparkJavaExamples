package algorithm;

import sun.misc.LRUCache;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class TestMain {

    public static void main(String[] args) {

//        DoubleLink<String> doubleLink = new DoubleLink<String>();

        MyDoubleLinkList doubleLink = new MyDoubleLinkList();


        doubleLink.addHead("dasads");
        doubleLink.addHead("dasads2");
        doubleLink.addTail("dasads3");
//        doubleLink.inesert(0, "tt2");
//        doubleLink.inesert(1, "tt2");
//        doubleLink.inesert(2, "tt2");
//        doubleLink.inesert(3, "tt3");
//        doubleLink.inesert(4, "tt4");
//        doubleLink.inesert(5, "tt6");

//        LinkedList

        System.out.println(doubleLink.getSize());

        doubleLink.print();

        System.out.println("下标为0的值："+ doubleLink.getNode(0).data);




        ReferenceQueue<String> queue = new ReferenceQueue<String>();
        PhantomReference<String> pr = new PhantomReference<String>(new String("hello"), queue);
        System.out.println(pr.get());



        int a = 10;
        int b = 20;
        /* 调用方法1*/
        method1(a,b); //要求在方法1被调用之后打印出a=100 b=200 请写出method1的代码
        System.out.println("a="+a);
        System.out.println("b="+b);

//        LinkedHashMap
//        LRUCache
//        HashMap


    }

    public static void method1(int a,int b){
        System.out.println("a=" +a*10);
        System.out.println("b=" + b*10);
        System.exit(0);
    }

}
