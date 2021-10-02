package io.udp;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

/**
 * @author yuhangbin
 * @date 2021/6/26
 **/
@Slf4j
public class Client {


    public static void main(String[] args) {
        try {
            DatagramSocket clientSocket = new DatagramSocket(12000);
            // send request
            byte[] request = "hello server".getBytes();
            DatagramPacket packet = new DatagramPacket(request, request.length, new InetSocketAddress("127.0.0.1", 20000));
            clientSocket.send(packet);
            log.info("send request to server.");
            // receive response
            byte[] response = new byte[256];
            packet = new DatagramPacket(response, response.length);
            while (packet.getLength() > 0) {
                clientSocket.receive(packet);
                // print response
                log.info("response:{}", new String(response));
            }
            clientSocket.close();
            log.info("close clientSocket.");
        }catch (Exception e){
            log.error(ExceptionUtils.getStackTrace(e));
        }
    }
}
