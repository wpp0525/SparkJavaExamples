package concurrent;

public class ThreadLoop {
// 子线程循环10，主线程循环20次
    public static void main(String[] args) {

        for (int i = 0; i < 5 ;i ++){
            System.out.println("================="+ i +" ====================");
            try {
                Thread sub = new Thread(new SubThread());
                sub.start();
                sub.join();

                for (int j =0; j< 15; j++) {
                    System.out.println("主线程：" + Thread.currentThread().getName() +",循环次数：" + j);
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
//                System.out.println("数据异常，继续执行。 loop " + i);
//                continue;
            }
        }
    }


}

class SubThread implements Runnable{

    @Override
    public void run() {
        for (int i =0; i< 10; i++) {
            System.out.println("子线程：" + Thread.currentThread().getName() +",循环次数：" + i);
        }
    }
}
