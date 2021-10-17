package reactor;

import java.nio.channels.SelectionKey;

/**
 * @author yuhangbin
 * @date 2021/10/16
 **/
public interface EventHandler {

    void handleEvent(SelectionKey selectionKey);
}
