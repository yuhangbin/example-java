import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.function.BiConsumer;

/**
 * @author yuhangbin
 * @date 2021/6/30
 **/
@Slf4j
public class Test{

    public static void main(String[] args) {
        List<Thread> threads = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            final Integer num = i;
            threads.add(new Thread(() -> {
                System.out.println(num);
            }));
        }
        threads.forEach(x -> {
            x.start();
            try {
                x.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
    
}
