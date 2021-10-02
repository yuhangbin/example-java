package nio.tcp;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

/**
 * @author yuhangbin
 * @date 2021/6/27
 **/
public class EchoServer {

    private Selector selector;
    private InetSocketAddress socketAddress;

    public EchoServer(String hostname, int port) {
        socketAddress = new InetSocketAddress(hostname, port);
    }

    public void startServer(String hostname, String port) {
        try {
            this.selector = Selector.open();
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(socketAddress);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            while (true) {
                this.selector.select();
                Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
                while (keys.hasNext()) {
                    SelectionKey key = keys.next();
                    keys.remove();
                    if (!key.isValid()) {
                        continue;
                    }
                    if (key.isAcceptable()) {
                        accept(key);
                    }
                    if (key.isReadable()) {
                        read(key);
                    }
                }
            }
        }catch (Throwable e) {
            e.printStackTrace();
        }

    }

    public void accept(SelectionKey key) {

    }

    public void read(SelectionKey key) {

    }

}
