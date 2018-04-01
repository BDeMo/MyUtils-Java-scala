package Mytraining.Thread.DeadLockTest;
/**
 * Deadlock happens not in a thread pool.
 * @author SamJ
 *
 */
public class DeadLockWithoutThreadPool {

	public static void main(String[] args) {
		Object o1 = new Object();
        Object o2 = new Object();
        new Thread(new MultiThread(o1, o2, 0), "flag = 0").start();
        new Thread(new MultiThread(o1, o2, 1), "flag = 1").start();
	}

}
