package hashmap;


import java.math.BigInteger;

class A{
    static{
        System.out.println("1");
    }
    public A(){
        System.out.println("2");
    }
}
class B extends A{
    static{
        System.out.println("a");
    }
    public B(){
        System.out.println("类名： " + super.getClass().getName());
        System.out.println("super类名： " + super.getClass().getSuperclass().getName());
        System.out.println("b");
    }
}
public class Hello extends A{

    public static void main(String[] ars){
        A ab = new B(); //执行到此处,结果: 1a2b

        //　注:类的static 代码段,可以看作是类首次加载(被虚拟机加载)执行的代码,而对于类的加载,
        // 首先要执行其基类的构造,再执行其本身的构造
//        ab = new B(); //执行到此处,结果: 1a2bab


//        BigInteger a = new BigInteger(323433);
//        a.add(new BigInteger(433434));]

        byte b1 =1,b2=2,b3,b6;
//        byte b1 =1,b2=2,b3,b6;
        final  byte b4 =4,b5=5;
        b6 = b4+b5;
        System.out.println(b6);
//        b3 =(byte) (b1+b2);
//        b3 =b1+b2;
        System.out.println(b1+127);

        StringBuffer a = new StringBuffer("a");
        StringBuffer b = new StringBuffer("b");

        operater(a,b);
        System.out.println(a +"," + b);

    }

    public static void operater(StringBuffer a,StringBuffer b){
        a.append(b); b=a;
    }
}