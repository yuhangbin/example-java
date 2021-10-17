package nio.tcp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

/**
 * @author yuhangbin
 * @date 2021/6/27
 **/
public class EchoServer {

    private Selector selector;
    private InetSocketAddress socketAddress;
    private final String CLOSE = "bye";

    public EchoServer(String hostname, int port) {
        socketAddress = new InetSocketAddress(hostname, port);
    }

    public void start() {
        try {
            this.selector = Selector.open();
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(socketAddress);
            // accept client's connection
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
                        register(selector, serverSocketChannel);
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

    public void register(Selector selector, ServerSocketChannel serverSocketChannel) throws IOException {
        // prepare server reads client message after connected
        SocketChannel client = serverSocketChannel.accept();
        client.configureBlocking(false);
        client.register(selector, SelectionKey.OP_READ);
        System.out.println("register client: " + client.getRemoteAddress());
    }

    public void read(SelectionKey key) throws IOException {
        SocketChannel client = (SocketChannel) key.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        client.read(byteBuffer);
        byteBuffer.flip();
        byte[] bytes = new byte[byteBuffer.limit()];
        if (bytes.length == 0) {
            client.close();
            return;
        }
        byteBuffer.get(bytes);
        String clientMessage = new String(bytes, StandardCharsets.UTF_8);
        System.out.println("client message: " + clientMessage);
        // echo
        client.write(ByteBuffer.wrap(bytes));
        byteBuffer.clear();
        // if client wants to close
        if (clientMessage.equals(CLOSE)){
            client.close();
        }
    }

    public static void main(String[] args) {
        EchoServer echoServer = new EchoServer("localhost", 54321);
        echoServer.start();
    }

}
