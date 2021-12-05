package reactor.framework;

import java.nio.channels.SelectionKey;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author yuhangbin
 * @date 2021/12/5
 **/
public class ThreadPoolDispatcher implements Dispatcher{

    private ThreadPoolExecutor executor;
    private Map<EventEnum, EventHandler> handlerMap = new HashMap<>();

    public ThreadPoolDispatcher(int nThread) {
        this.executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(nThread);
    }

    @Override
    public void handleEvent(AbstractNioChannel channel, Object clientMessage, SelectionKey key) {
        executor.execute(() -> {
            channel.getEventHandler().handleEvent(channel, clientMessage, key);
        });
    }

    @Override
    public Dispatcher registerHandler(EventEnum eventEnum, EventHandler eventHandler) {
        handlerMap.put(eventEnum, eventHandler);
        return this;
    }

    @Override
    public Dispatcher removeHandler(EventEnum eventEnum, EventHandler eventHandler) {
        handlerMap.remove(eventEnum);
        return this;
    }

    @Override
    public void stop() {
        try {
            executor.shutdown();
            executor.awaitTermination(5, TimeUnit.SECONDS);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
