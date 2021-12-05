package reactor.framework;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author yuhangbin
 * @date 2021/10/16
 **/
public class Reactor {

    private Dispatcher dispatcher;
    // Synchronous Event De-Multiplexer
    private Selector selector;
    // executor select() and dispatcher event
    private ThreadPoolExecutor bossExecutor = (ThreadPoolExecutor) Executors.newSingleThreadExecutor();

    private List<AbstractNioChannel> channels = new LinkedList<>();

    public Reactor(Dispatcher dispatcher) throws IOException {
        this.dispatcher = dispatcher;
        this.selector = Selector.open();
    }

    public void start() {
        try {
            eventLoop();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void stop() {
        try {
            bossExecutor.shutdown();
            bossExecutor.awaitTermination(5, TimeUnit.SECONDS);
            this.dispatcher.stop();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Reactor registerChannel(AbstractNioChannel channel) throws IOException {
        SelectionKey key = channel.getJavaChannel().register(selector, channel.getInterestOps());
        key.attach(channel);
        channel.setReactor(this);
        this.channels.add(channel);
        return this;
    }

    private void eventLoop() throws IOException {
        while (!Thread.interrupted()) {
            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();
            while (iterator.hasNext()) {
                var key = iterator.next();
                if (!key.isValid()) {
                    iterator.remove();
                    continue;
                }
                processKey(key);
            }
            keys.clear();
        }
    }

    private void processKey(SelectionKey key) {
        //todo
    }


}
