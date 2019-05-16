package concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AwaitNotifty {

    public static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException ,Exception,IllegalMonitorStateException{


       Thread aa =  new Thread( new Runnable(){
            @Override
            public void run() {
                lock.lock();
                System.out.println("先执行子线程，在执行主线程");
                countDownLatch.countDown();  //第一种方法
                lock.unlock();
            }
        });
        aa.start();
//        aa.join();                        //第二种方法
           countDownLatch.await() ;


        System.out.println("主线程执行");

    }
}
