package concurrent.lock;

public class DeadLock {

    public static void main(String[] args) {

        DeadThread deadThread = new DeadThread();

        new Thread( deadThread).start();
        new Thread(deadThread).start();


//        DeadThread deadThread = new DeadThread();
//
//        new Thread( deadThread).start();
//        new Thread(deadThread).start();
    }
}

class DeadThread implements  Runnable{

    private Object o1 = new Object();
    private Object o2 = new Object();

    private  boolean flag = true;

    @Override
    public void run() {

        if(flag){
            flag = false;
            synchronized (o1){
                System.out.println(Thread.currentThread().getName() +"锁着o1，等待拿到o2");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2){
                    System.out.println("先获取lock1锁，在获取lock2锁");
                }
            }

        }else{
            flag = true;
            synchronized (o2){
                System.out.println(Thread.currentThread().getName() +"锁着o2，等待拿到1");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1){
                    System.out.println("先获取lock1锁，在获取lock2锁");
                }
            }
        }

    }
}

