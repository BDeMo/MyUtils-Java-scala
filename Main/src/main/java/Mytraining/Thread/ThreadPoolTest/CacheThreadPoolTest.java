package Mytraining.Thread.ThreadPoolTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CacheThreadPoolTest {

    public static void main(String[] args) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ExecutorService cacheThreadPool = Executors.newCachedThreadPool();

        for(int i = 0; i<10 ;i++) {
            final int index = i;
            try {
                Thread.sleep(index * 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            cacheThreadPool.execute(() -> {
                System.out.println(index);
            });
        }
        cacheThreadPool.shutdown();
    }
}
