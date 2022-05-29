package redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@SpringBootTest(classes = elvis.App.class)
public class RedisTest {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    void contextLoads() {
        redisTemplate.opsForValue().set("name", "张三");
        redisTemplate.opsForValue().set("k3", "v3");
        System.out.println(redisTemplate.opsForValue().get("name"));
        redisTemplate.expire("k3", 60, TimeUnit.SECONDS);
        System.out.println(redisTemplate.getExpire("k3"));
        //获取所有的key
        Set<String> keys = redisTemplate.keys("*");
        System.out.println(keys);
    }

    @Test
    void testList() {
        System.out.println(redisTemplate.opsForList().size("mylist"));
    }

    @Test
    void testString() {
        ValueOperations<String, Object> forValue = redisTemplate.opsForValue();//操作字符串类型
        forValue.set("k1", "v1");
        System.out.println(forValue.get("k1"));

        Map<String, String> map = new HashMap<>();
        map.put("k9", "v9");
        map.put("k10", "v10");
        forValue.multiSet(map);
    }

    @Test
    void testRDB() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Instant.now() + ": " + i);
            redisTemplate.opsForValue().set("hello" + i, "world");
        }
    }

    @Test
    void testScan() {
        ScanOptions options = ScanOptions.scanOptions().match("hello*").count(1000).build();
        Cursor<String> cursor = (Cursor<String>) redisTemplate.executeWithStickyConnection(
                redisConnection -> new ConvertingCursor<>(redisConnection.scan(options),
                        redisTemplate.getKeySerializer()::deserialize));

        cursor.forEachRemaining(key -> {
            System.out.println(LocalDateTime.now() + ": " + key);
            redisTemplate.delete(key);
        });
    }
}
