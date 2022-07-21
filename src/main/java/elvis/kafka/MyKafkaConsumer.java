package elvis.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class MyKafkaConsumer {
    @KafkaListener(topics = {"test"})
    public void onMessage(ConsumerRecord<?, ?> consumerRecord) {
        Optional<?> optional = Optional.ofNullable(consumerRecord.value());
        if (optional.isPresent()) {
            Object msg = optional.get();
            System.out.println("message received:" + msg);
            log.info("message:{}", msg);
        }
    }

    @Bean
    public NewTopic createIfNotExist() {
        return new NewTopic("test-zy", 1, (short) 1);
    }
}
