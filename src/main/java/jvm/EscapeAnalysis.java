package jvm;

/**
 *  逃逸分析
 */

public class EscapeAnalysis {

    public static B b;
    /**
     * <p>全局变量赋值发生指针逃逸</p>
     * 发生方法逃逸， 该对象必须分配到堆上，需要gc来进行回收，比较麻烦。
     */
    public void globalVariablePointerEscape() {
        b = new B();
    }
    /**
     * <p>方法返回引用，发生指针逃逸</p>
     * @return
     */
    public B methodPointerEscape() {
        return new B();
    }

    /**
     * 没有发生方法逃逸， 该对象可以直接在 “栈上分配”，等到函数结束后，则内存自动销毁
     */
    public void method() {
        EscapeAnalysis test = new EscapeAnalysis();
        //处理逻辑
        test = null;
    }
    /**
     * <p>实例引用发生指针逃逸</p>
     */
    public void instancePassPointerEscape() {
        methodPointerEscape().printClassName(this);
    }

    class B {
        public void printClassName(EscapeAnalysis clazz) {
            System.out.println(clazz.getClass().getName());
        }
    }

    public static void main(String[] args) {
        //简单测试
//        EscapeAnalysis escapeAnalysis = new EscapeAnalysis();
//        escapeAnalysis.instancePassPointerEscape();


      // 一个不使用逃逸分析  -server -Xmx10m -Xms10m -XX:-DoEscapeAnalysis -XX:+PrintGC
      // 一个使用逃逸分析  -server -Xmx10m -Xms10m -XX:+DoEscapeAnalysis -XX:+PrintGC
        long b = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            alloc();
        }
        long e = System.currentTimeMillis();
        System.out.println(e - b);
    }

    public static void alloc() {
        byte[] b = new byte[2];
        b[0] = 1;
    }

}