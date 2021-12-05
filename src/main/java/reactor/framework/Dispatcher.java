package reactor.framework;

import java.nio.channels.SelectionKey;

/**
 * @author yuhangbin
 * @date 2021/10/16
 **/
public interface Dispatcher {

    void handleEvent(AbstractNioChannel channel, Object clientMessage, SelectionKey key);

    Dispatcher registerHandler(EventEnum eventEnum, EventHandler eventHandler);

    Dispatcher removeHandler(EventEnum eventEnum, EventHandler eventHandler);

    void stop();
}
