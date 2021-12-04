package base.concurrent;

import base.concurrent.util.ConcurrencyTool;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author yuhangbin
 * @date 2021/11/11
 **/
public class BlockingQueueTest {

    @Test
    void testBlocking() throws Exception{
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(1);
        queue.put(1);
        // block forever cause by this queue has no space
        queue.put(2);
    }

    @Test
    void testPutAndTake() throws Exception {
        int taskCount = 1000000;
        List<Integer> integerList = Collections.synchronizedList(new LinkedList<>());
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(taskCount);
        for (int i = 0; i < taskCount; i++) {
            queue.put(i);
        }
        ConcurrencyTool.concurrencyTest(10, taskCount, () -> {
            try {
                integerList.add(queue.take());
            }catch (Exception e) {

            }
        });
        Assertions.assertEquals(taskCount, integerList.size());
    }
}
