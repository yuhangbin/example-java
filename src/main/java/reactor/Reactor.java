package reactor;

import java.nio.channels.Selector;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author yuhangbin
 * @date 2021/10/16
 **/
public class Reactor {

    private Dispatcher dispatcher;
    // Synchronous Event De-Multiplexer
    private Selector selector;
    // executor select() and dispatcher event
    private ThreadPoolExecutor bossExecutor;
    // executor event handle
    private ThreadPoolExecutor workerExecutor;



}
