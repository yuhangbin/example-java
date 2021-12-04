package nio.tcp;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Can't send empty message to server.(Implement by OS)
 * @author yuhangbin
 * @date 2021/6/27
 **/
public class EchoClient {


    private String hostname;
    private int port;
    private SocketChannel client;
    private ByteBuffer buffer;

    public EchoClient(String hostname, int port) {
        try {
            this.hostname = hostname;
            this.port = port;
            client = SocketChannel.open(new InetSocketAddress("127.0.0.1", 54321));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void send(String message) throws Exception{
        System.out.println("send " + message + " to echo server");
        buffer = ByteBuffer.wrap(message.getBytes(StandardCharsets.UTF_8));
        client.write(buffer);
        buffer.clear();
        client.read(buffer);
        System.out.println("server echo :" + new String(buffer.array(), StandardCharsets.UTF_8));
    }

    public static void main(String[] args) throws Exception{
        EchoClient echoClient = new EchoClient("localhost", 54321);
//        EchoClient echoClient1 = new EchoClient("localhost", 54321);
        Scanner scanner = new Scanner(System.in);
        String message;
        while ((message = scanner.nextLine()) != null) {
//            if (message.contains("2")){
//                echoClient1.send(message);
//            }
            if (message.contains("1")) {
                echoClient.send("");
            }
        }
    }
}
