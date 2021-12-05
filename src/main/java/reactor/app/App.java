package reactor.app;

import reactor.framework.Dispatcher;
import reactor.framework.EventEnum;
import reactor.framework.Reactor;
import reactor.framework.ThreadPoolDispatcher;

/**
 * @author yuhangbin
 * @date 2021/12/5
 **/
public class App {


    public static void main(String[] args) {
        try {
            Dispatcher dispatcher = new ThreadPoolDispatcher(10);
            dispatcher.registerHandler(EventEnum.READ, new LoggingHandler());
            Reactor reactor = new Reactor(dispatcher);
            reactor.start();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
