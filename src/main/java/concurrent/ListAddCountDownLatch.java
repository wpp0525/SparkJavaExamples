package concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author alienware
 *
 */
public class ListAddCountDownLatch {
	private volatile static List list = new ArrayList();	
	
	public void add( int i){
		list.add("bjsxt" + i) ;
	}
	public int size(){
		return list.size();
	}
	
	public static void main(String[] args) {
		final ListAddCountDownLatch list3 = new ListAddCountDownLatch();

		final CountDownLatch countDownLatch = new CountDownLatch(1);

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
						System.out.println("t1启动..");
						for(int i = 0; i <10; i++){
							list3.add(i);
							System.out.println("当前线程：" + Thread.currentThread().getName() + "添加了一个元素..");
							Thread.sleep(500);
							if(list3.size() == 5){
								System.out.println("已经发出通知..");
								countDownLatch.countDown();
							}
						}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "t1");
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("t2启动..");
				try {
					countDownLatch.await();
				}catch (InterruptedException e){
					e.printStackTrace();
				}
				System.out.println("t2 继续执行....");

				System.out.println("当前线程：" + Thread.currentThread().getName() + "收到通知线程停止..");
				throw new RuntimeException();
			}
		}, "t2");

		t1.start();
		t2.start();

		try {
		 Thread.sleep(10000);
		}catch (InterruptedException E){
			E.printStackTrace();
		}
		System.out.println("main Thread exit....");

		for (Object row : list) {
			System.out.println(row);
		}
		
	}
	
}
