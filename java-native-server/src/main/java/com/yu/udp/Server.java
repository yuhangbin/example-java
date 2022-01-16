package com.yu.udp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.LinkedList;
import java.util.List;

/**
 * https://docs.oracle.com/javase/tutorial/networking/datagrams/index.html
 * @author yuhangbin
 * @date 2021/6/26
 **/
@Slf4j
public class Server {

    private static final List<String> messages = new LinkedList<>();

    static {
        messages.add("hello everyone!");
        messages.add("I'm server.");
    }

    public static void main(String[] args) {
        try {
            DatagramSocket serverSocket = new DatagramSocket(20000);
            new Thread(new Work(serverSocket)).start();
        }catch (Exception e) {
         log.error(ExceptionUtils.getStackTrace(e));
        }
    }

    @AllArgsConstructor
    public static class Work implements Runnable {
        private final DatagramSocket serverSocket;

        @Override
        public void run() {
            try {
                // receive client
                byte[] buffer = new byte[256];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                serverSocket.receive(packet);
                log.info("receive client request: {}", new String(packet.getData()));
                // send message to client
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                for (String message : messages) {
                    byte[] bytes = message.getBytes();
                    DatagramPacket response = new DatagramPacket(bytes, 0, bytes.length, address, port);
                    serverSocket.send(response);
                    log.info("send to {}:{} message:{}", address.getAddress(), port, message);
                }
            }catch (Exception e) {
                log.error(ExceptionUtils.getStackTrace(e));
            }

        }
    }
}
