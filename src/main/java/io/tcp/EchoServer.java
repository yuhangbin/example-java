package io.tcp;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * A TCP server support multiply client.
 * Disconnect when client send "bye".
 * https://docs.oracle.com/javase/tutorial/networking/sockets/readingWriting.html
 * @author yuhangbin
 * @date 2021/6/26
 **/
@Slf4j
public class EchoServer {

    private static final int port = 30000;

    public static void main(String[] args) throws Exception{
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
                executor.execute(new Work(serverSocket.accept()));
            }
        }
    }


    public static class Work implements Runnable {

        private final Socket socket;

        public Work(Socket socket) {
            this.socket = socket;
        }
        @Override
        public void run() {
            log.info("client socket: localAddress:{}, localPort:{}", socket.getLocalAddress(), socket.getLocalPort());
            log.info("client socket: remoteAddress:{}, remotePort:{}", socket.getInetAddress(), socket.getPort());
            try(BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
                String request;
                while ((request = in.readLine()) != null) {
                    if ("bye".equals(request)){
                        log.info("disconnected");
                        break;
                    }
                    log.info("client's request :{}", request);
                    out.println(request);
                    log.info("send response to client.");
                }
            }catch (Exception e){
                log.error(ExceptionUtils.getStackTrace(e));
            }
        }
    }
}
