package com.yu.tcp;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * A TCP client.
 * @author yuhangbin
 * @date 2021/6/26
 **/
@Slf4j
public class EchoClient {

    public static void main(String[] args) {
        try {
            // connect to server
//            Socket socket = new Socket("127.0.0.1",30000);
            Socket socket = new Socket("2408:4000:200::10b",20010);
            try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))){
                // send request to server
                out.println("hello!");
                // get response from server
                log.info("get response :{}", in.readLine());
                out.println("bye");
                log.info("disconnected");
            }
        }catch (Exception e){
            log.error(ExceptionUtils.getStackTrace(e));
        }
    }
}
