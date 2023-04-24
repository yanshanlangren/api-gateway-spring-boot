package kafka;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.junit.jupiter.api.Test;
import org.slf4j.impl.StaticLoggerBinder;

import java.util.*;

public class KafkaConsumerTest {

    private static final String BOOTSTRAP_SERVERS = "i-oeruks5g:9092";

    private Logger setLogLevel(){
        LoggerContext loggerContext = (LoggerContext) StaticLoggerBinder.getSingleton().getLoggerFactory();
        Logger logger = loggerContext.getLogger("ROOT");
        logger.setLevel(Level.INFO);
        return logger;
    }

    @Test
    public void autoCommitConsumerTest() {
        Logger logger = setLogLevel();


        Properties props = new Properties();
        props.put("bootstrap.servers", BOOTSTRAP_SERVERS);
        props.put("group.id", "test");
        // 自动提交开关
        props.put("enable.auto.commit", "true");
        // 自动提交的时间间隔，此处是1s
        props.put("auto.commit.interval.ms", "3600000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("test"));
        while (true) {
            // 调用poll后，1000ms后，消息状态会被改为 committed
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records)
                logger.info(record.toString());
//                insertIntoDB(record); // 将消息入库，时间可能会超过1000ms
        }
    }

    @Test
    public void manualCommitConsumerTest() {
        Logger logger = setLogLevel();

        Properties props = new Properties();
        props.put("bootstrap.servers", BOOTSTRAP_SERVERS);
        props.put("group.id", "test");
        // 关闭自动提交，改为手动提交
        props.put("enable.auto.commit", "false");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("test"));

        final int minBatchSize = 200;
        List<ConsumerRecord<String, String>> buffer = new ArrayList<>();
        while (true) {
            // 调用poll后，不会进行auto commit
            ConsumerRecords<String, String> records = consumer.poll(1000);
            for (ConsumerRecord<String, String> record : records) {
                buffer.add(record);
            }
            if (buffer.size() >= minBatchSize) {
                //TODO do something
                for(ConsumerRecord<String, String> record : records){
                    logger.info(record.toString());
                }
                logger.info("buffer size["+buffer.size()+"] dealt");
//                insertIntoDb(buffer);
                // 所有消息消费完毕以后，才进行commit操作
//                consumer.commitSync();
                buffer.clear();
            }
        }
    }

    @Test
    public void manualCommitLowTest() {
        Logger logger = setLogLevel();

        Properties props = new Properties();
        props.put("bootstrap.servers", BOOTSTRAP_SERVERS);
        props.put("group.id", "test");
        //关闭自动提交
        props.put("enable.auto.commit", "false");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("test"));

        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Long.MAX_VALUE);
                for (TopicPartition partition : records.partitions()) {
                    List<ConsumerRecord<String, String>> partitionRecords = records.records(partition);
                    for (ConsumerRecord<String, String> record : partitionRecords) {
                        logger.info(record.offset() + ": " + record.value());
                    }
                    long lastOffset = partitionRecords.get(partitionRecords.size() - 1).offset();
                    // 精确控制offset
                    consumer.commitSync(Collections.singletonMap(partition, new OffsetAndMetadata(lastOffset + 1)));
                }
            }
        } finally {
            consumer.close();
        }
    }
}
