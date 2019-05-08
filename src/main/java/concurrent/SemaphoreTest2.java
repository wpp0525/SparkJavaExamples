package concurrent;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest2 {


    public static void main(String[] args) {

        final  Semaphore windows = new Semaphore(5);  // 声明5个窗口

        for (int i = 0; i < 8; i++) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date d = new Date();
                        String dateNowStr = sdf.format(d);

                        windows.acquire();  // 占用窗口
                        System.out.println(dateNowStr +":" +Thread.currentThread().getName() + ": 开始买票");
                        Thread.sleep(2000);  // 睡2秒，模拟买票流程

                        Date d2 = new Date();
                        String dateNowStr2 = sdf.format(d2);
                        System.out.println(dateNowStr2 +":" +Thread.currentThread().getName() + ": 购票成功");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        windows.release();  // 释放窗口
                    }
                }
            }.start();
        }
    }
}