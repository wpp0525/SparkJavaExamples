package concurrent;


import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {

    private static final int THREADS_CONUT = 2;

    public static AtomicInteger count = new AtomicInteger(0);

    public static void increase() {
        count.incrementAndGet();
    }

    public static void main(String[] args)  throws  InterruptedException{

        Thread[] threads = new Thread[THREADS_CONUT];

        for (int i = 0; i < THREADS_CONUT; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        increase();
                    }
                }
            });
            threads[i].start();
        }

//        System.out.println("活跃进程：" + Thread.activeCount());
//        for (int i = 0; i < THREADS_CONUT; i++) {
//            threads[i].join();
//        }
//        System.out.println("活跃进程2：" + Thread.activeCount());

        if(Thread.activeCount() <= 2){
            Thread.yield();
        }

        System.out.println(count);

        System.out.println("活跃进程3：" + Thread.activeCount());

        System.out.println("//＾▽＾//\n".length());
    }
}
