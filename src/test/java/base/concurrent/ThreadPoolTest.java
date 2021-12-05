package base.concurrent;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * https://www.baeldung.com/java-executor-service-tutorial
 * @author yuhangbin
 * @date 2021/12/5
 **/
public class ThreadPoolTest {

    private ExecutorService executor;

    @BeforeEach
    void setUp() {
        executor = Executors.newFixedThreadPool(10);
    }

    /**
     * The shutdownNow() method tries to destroy the ExecutorService immediately,
     * but it doesn't guarantee that all the running threads will be stopped at the same time
     */
    @Test
    void testShutdownNow() {
        AtomicInteger taskCount = new AtomicInteger(0);
        for (int i = 0; i < 2; i++) {
            executor.execute(() -> {
                try {
                    for (int j = 0; j < 10; j++) {
                        Thread.sleep(100);
                        System.out.printf("%s - %s\n", Thread.currentThread().getName(), j);
                    }
                    taskCount.incrementAndGet();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        executor.shutdownNow();
        try {
            executor.awaitTermination(5, TimeUnit.SECONDS);
        }catch (Exception e){
            e.printStackTrace();
        }
        Assertions.assertEquals(0, taskCount.get());
    }

    /**
     * The shutdown() method doesn't cause immediate destruction of the ExecutorService.
     * It will make the ExecutorService stop accepting new tasks and shut down after all running threads finish their current work
     */
    @Test
    void testShutdown() {
        AtomicInteger taskCount = new AtomicInteger(0);
        for (int i = 0; i < 2; i++) {
            executor.execute(() -> {
                try {
                    for (int j = 0; j < 10; j++) {
                        Thread.sleep(100);
                        System.out.printf("%s - %s\n", Thread.currentThread().getName(), j);
                    }
                    taskCount.incrementAndGet();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        executor.shutdown();
        try {
            executor.awaitTermination(5, TimeUnit.SECONDS);
        }catch (Exception e){
            e.printStackTrace();
        }
        Assertions.assertEquals(2, taskCount.get());
    }
}
