package base.concurrent.util;

import java.util.concurrent.*;

/**
 * @author yuhangbin
 * @date 2021/11/25
 **/
public class CompletionServiceExample {

    public static void main(String[] args) throws Exception {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);
        CompletionService<Integer> service = new ExecutorCompletionService<>(executor);
        service.submit(() -> {
            for (int i = 0; i < 1000000; i++) {
                Math.random();
            }
            System.out.println(1);
            return 1;
        });
        service.submit(() -> {
            for (int i = 0; i < 1000000; i++) {
                Math.random();
            }
            System.out.println(2);
            return 2;
        });
        service.submit(() -> {
            for (int i = 0; i < 1000000; i++) {
                Math.random();
            }
            System.out.println(3);
            return 3;
        });
        for (int i = 0; i < 3; i++) {
            System.out.println(service.take().get());
        }
        executor.shutdownNow();
    }
}
