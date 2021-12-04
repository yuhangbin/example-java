package thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author yuhangbin
 * @date 2021/10/28
 **/
public class ThreadInterception {


    public static class GoodProducer extends Thread {
        private BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(1000);

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println(Thread.currentThread().getName());
                System.out.println("add " + Thread.currentThread().getName());
                queue.add(1);
            }
        }

        public void consumer() {
//            while ()
        }

        public void cancel() {
            Thread.currentThread().interrupt();
            System.out.println("cancel " + Thread.currentThread().getName());
            System.out.println(queue.size());
        }
    }

    public static class BadProducer extends Thread {
        private BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(1000);
        private volatile boolean cancel = false;

        @Override
        public void run() {
            while (!cancel) {
                System.out.println("add" + Thread.currentThread().getName());
                queue.add(1);
            }
        }
        public void cancel() {
            System.out.println("cancel" + Thread.currentThread().getName());
            cancel = true;
            System.out.println(queue.size());
        }
    }

    public static void main(String[] args) throws Exception{
        System.out.println(Thread.currentThread().getName());
        GoodProducer producer = new GoodProducer();
        producer.start();
        producer.cancel();
//        BadProducer producer = new BadProducer();
//        producer.start();
//        producer.cancel();
    }
}
