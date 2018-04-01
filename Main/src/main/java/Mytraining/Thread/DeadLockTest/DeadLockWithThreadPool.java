package Mytraining.Thread.DeadLockTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * DeadLock happens in a thread pool.
 * @author SamJ
 *
 */
public class DeadLockWithThreadPool {

	public static void main(String[] args) {
		Object o1 = new Object();
        Object o2 = new Object();

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int flag = 0; flag < 2; flag++) {
            executorService.execute(new MultiThread(o1, o2, flag));
        }
	}
	
}
