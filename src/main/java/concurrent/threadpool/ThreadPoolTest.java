package concurrent.threadpool;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {

    //让可执行程序休息一下
//    private static int executePrograms = 2000; //如果有时间来执行，则所有都可以计算
    private static int executePrograms = 0; //如果为0，则会执行相关策略
    private static int produceTaskMaxNumber = 15;

    // produceTaskMaxNumber=10 ,则 1,2直接运行，  3,4 放到新起的线程中，567放到任务队列中，8,9,10直接抛弃

    public static void main(String[] args) {

        //CallerRunsPolicy 策略，如果线程处理不过来，则会使用调用线程处理问题
        // 构造一个线程池
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                2,
                4, // 一个thread 一次只能执行一个任务，
                3,//线程空闲3s，则退出
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3), //任务队列的大小为3
//                new ThreadPoolExecutor.CallerRunsPolicy()  //任务不会丢失
                new ThreadPoolExecutor.DiscardPolicy()
//                new ThreadPoolExecutor.DiscardOldestPolicy()
//                new ThreadPoolExecutor.AbortPolicy()
        );
//        ThreadPoolExecutor threadPool = new ThreadPoolExecutor();
        for (int i = 1; i <= produceTaskMaxNumber; i++) {
            try {
                String task = "task@ " + i;
                System.out.println("put " + task);
                threadPool.execute(new ThreadPoolTask(task));

                // 便于观察，等待一段时间
                Thread.sleep(executePrograms);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }



        //关闭线程池

//        threadPool.shutdownNow();
//        threadPool.shutdown();



    }

}

//启动一个线程进行相关处理
class ThreadPoolTask implements Runnable {
    // 保存任务所需要的数据
    private Object threadPoolTaskData;
    private static int consumerTaskSleepTime = 2000;

    ThreadPoolTask(Object tasks) {
        this.threadPoolTaskData = tasks;
    }

    public void run() {
        // 处理一个任务，这里的处理方式太简单了，仅仅是一个打印语句
        System.out.println("start .." + threadPoolTaskData);
        try {
            //便于观察，等待一段时间
            Thread.sleep(consumerTaskSleepTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("finish " + threadPoolTaskData);
        threadPoolTaskData = null;
    }

}