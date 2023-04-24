package kafka;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Slf4j
@SpringBootTest(classes = elvis.App.class)
public class KafkaProducerTest {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @SneakyThrows
    @Test
    public void produce() {
        int i = 0;
        while (true) {
            asyncSend("hello world-" + i++);
            Thread.sleep(1000);
        }
    }

    private void asyncSend(String msg) {
        kafkaTemplate.send("test", msg);
        log.info("send:{}", msg);
    }

    private void syncSend(String msg) {
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send("test", msg);
        //注意，可以设置等待时间，超出后，不再等候结果
        SendResult<String, Object> result = null;
        try {
            result = future.get(10, TimeUnit.MILLISECONDS);
            log.info("send result:{}", result.getProducerRecord().value());
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }
    }
}
