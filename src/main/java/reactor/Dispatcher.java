package reactor;

import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;

/**
 * @author yuhangbin
 * @date 2021/10/16
 **/
public interface Dispatcher {

    void handleEvent(SelectionKey key);

    Dispatcher registerHandler(EventHandler eventHandler);

    Dispatcher removeHandler(EventHandler eventHandler);
}
