package reactor.app;

import lombok.extern.slf4j.Slf4j;
import reactor.framework.AbstractNioChannel;
import reactor.framework.EventHandler;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.charset.StandardCharsets;

/**
 * @author yuhangbin
 * @date 2021/12/5
 **/
@Slf4j
public class LoggingHandler implements EventHandler {
    public final static byte[] ACK = "success".getBytes(StandardCharsets.UTF_8);

    @Override
    public void handleEvent(AbstractNioChannel channel, Object clientMessage, SelectionKey key) {
        if (clientMessage instanceof ByteBuffer) {
            doLogging(channel, key, (ByteBuffer)clientMessage);
            reply(channel, key);
        }else {
            throw new IllegalStateException("Unknown data received");
        }
    }

    private void doLogging(AbstractNioChannel channel, SelectionKey key, ByteBuffer message) {
        log.info(new String(message.array(), StandardCharsets.UTF_8));
    }

    private void reply(AbstractNioChannel channel, SelectionKey key) {
        try {
            channel.write(ByteBuffer.wrap(ACK), key);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
