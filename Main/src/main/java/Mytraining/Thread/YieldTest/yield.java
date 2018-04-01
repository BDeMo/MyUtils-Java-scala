package Mytraining.Thread.YieldTest;

public class yield {

	public static void main(String[] args) {  
        ThreadDemo demo = new ThreadDemo();  
        Thread thread = new Thread(demo, "111");  
        Thread thread1 = new Thread(demo, "222");  
        thread.start();  
        thread1.start();  
	    }
	}
  
class ThreadDemo implements Runnable {  
  
    @SuppressWarnings("static-access")
	@Override  
    public void run() {  
        for (int i = 0; i < 5; i++) {  
            if (i == 3) {  
                System.out.println("current thread :" + Thread.currentThread().getName());
                Thread.currentThread().yield();  
            }  
            System.out.println("executing thread :" + Thread.currentThread().getName());
        }
  
    }

}
