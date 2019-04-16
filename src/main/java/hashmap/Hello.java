package extends2;


class A{
    static{
        System.out.print("1");
    }
    public A(){
        System.out.print("2");
    }
}
class B extends A{
    static{
        System.out.print("a");
    }
    public B(){
        System.out.print("b");
    }
}
public class Hello{
    public static void main(String[] ars){
        A ab = new B(); //执行到此处,结果: 1a2b
        ab = new B(); //执行到此处,结果: 1a2bab
    }
}