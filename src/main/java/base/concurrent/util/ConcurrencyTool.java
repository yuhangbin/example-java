package base.concurrent.util;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author yuhangbin
 * @date 2021/8/28
 **/
public class ConcurrencyTool {

    public static void concurrencyTest(int concurrencyLevel, int taskCount, Runnable runnable) throws Exception {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(concurrencyLevel);
        CountDownLatch countDownLatch = new CountDownLatch(taskCount);
        for (int i = 0; i < taskCount; i++){
            executor.execute(()-> {
                runnable.run();
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executor.shutdownNow();
    }
}
