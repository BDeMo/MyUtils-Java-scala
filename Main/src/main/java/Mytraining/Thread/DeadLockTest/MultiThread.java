package Mytraining.Thread.DeadLockTest;

/**
 * <p>A thread to make deadlock happen if you run it.
 * @author SamJ
 *
 */
public class MultiThread implements Runnable {
	private Object o1;
    private Object o2;
    private int flag;
    
	/**
	 * A structure method of a deadlock-making thread.
	 * @throws InterruptedException
	 */
    public MultiThread(Object o1, Object o2, int flag) {
        this.o1 = o1;
        this.o2 = o2;
        this.flag = flag;
    }
    
    public void task1() throws InterruptedException{
        synchronized (o1){
            synchronized (o2){
                System.out.println(Thread.currentThread().getName());
            }
        }
    }

    public void task2() throws InterruptedException{
        synchronized (o2){
            Thread.yield();
            synchronized (o1) {
                System.out.println(Thread.currentThread().getName());
            }
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (this.flag == 1) {
                    this.task1();
                } else {
                    this.task2();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
}
