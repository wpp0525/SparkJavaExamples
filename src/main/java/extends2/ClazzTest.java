package extends2;

interface  A{
    int x = 12; // 接口的变量隐含着 public static final.  ，所以不能重新赋值
}

interface  A2{
    int x = 13; // 接口的变量隐含着 public static final.  ，所以不能重新赋值
}

interface  A3 extends A, A2{ //接口可以继承多个接口
    int x = 14; //
}
class B {
    int x =16;
}
class ClazzTest extends B implements A {

    public void pX(){
//        A.x = 3;
//        System.out.println(x); //必须指定是调用那个类的方法。
        System.out.println(A3.x);  // 接口的变量隐含着 public static final.  ，所以不能重新赋值
        System.out.println(super.x);
    }
    public static void main(String[] args) {
        new ClazzTest().pX();

        new ClazzTest().tt();

    }

    public  void  tt(){
        this.pX();
        pX();
    }
}
