package designpattern.proxy;

import java.lang.reflect.Proxy;

/**
 * 调用类
 * @author wpp
 * @date 2018/11/7 20:26
 */
public class Client {

    public static void main(String[] args) {
        //真实对象
        Subject realSubject =  new RealSubject();

        MyInvocationHandler myInvocationHandler = new MyInvocationHandler(realSubject);
        //代理对象
        Subject proxyClass = (Subject) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{Subject.class}, myInvocationHandler);
        Subject proxyClass2 = (Subject) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{Subject.class}, myInvocationHandler);

        System.out.println(proxyClass + ":::" + proxyClass2);
        proxyClass.sellBooks();
//        proxyClass.sellBooks();
        System.out.println( "sellBooks return : " + proxyClass.sellBooks());

        proxyClass.speak();
        System.out.println( "speak return : " + proxyClass.speak());

        System.out.println( "speak return : " + proxyClass.getClass());
    }
}