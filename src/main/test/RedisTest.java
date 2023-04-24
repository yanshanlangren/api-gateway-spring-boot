import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.io.Closeable;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.*;
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
    void createMap() {
        HashMap<String, Object> map = new HashMap<>();
        for (int i = 0; i < 1000; i++) {
            map.put("hello-" + i, UUID.randomUUID().toString());
        }
        redisTemplate.opsForHash().putAll("hmtest", map);

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
        long t0 = System.currentTimeMillis();
        ScanOptions options = ScanOptions.scanOptions().match("hello*").count(1000).build();
        Cursor<String> cursor = (Cursor<String>) redisTemplate.executeWithStickyConnection(redisConnection -> new ConvertingCursor<>(redisConnection.scan(options), redisTemplate.getKeySerializer()::deserialize));

        Set<String> res = new HashSet<>();
        cursor.forEachRemaining(key -> {
            res.add(key);
        });
        long t1 = System.currentTimeMillis();
        System.out.println("查询用时: " + (t1 - t0) + "ms");
    }

    @Test
    void testKeys() {
        long t0 = System.currentTimeMillis();
        Set<String> res = redisTemplate.keys("hello*");
//        res.forEach(key -> {
//            System.out.println(LocalDateTime.now() + ": " + key);
//        });
        long t1 = System.currentTimeMillis();
        System.out.println("查询用时: " + (t1 - t0) + "ms");
    }

    @Test
    void testHMScan() {
        ScanOptions options = ScanOptions.scanOptions().count(100).match("*").build();
        long t0 = System.currentTimeMillis();
        Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan("hmtest", options);
        while (cursor.hasNext()) {
            Map.Entry<Object, Object> entry = cursor.next();
            System.out.println("key:[" + entry.getKey() + "], value:[" + entry.getValue() + "]");
        }
        long t1 = System.currentTimeMillis();
        System.out.println("查询用时: " + (t1 - t0) + "ms");

//        RedisSerializer<String> redisSerializer = (RedisSerializer<String>) redisTemplate.getKeySerializer();
//        Cursor<Map.Entry<Object, Object>> cursor1 =
//                 redisTemplate
//                        .executeWithStickyConnection(new RedisCallback<Closeable>() {
//                            @Override
//                            public Closeable doInRedis(RedisConnection connection) throws DataAccessException {
////                                Closeable c = new ConvertingCursor<>(connection.scan(options), redisSerializer::deserialize);
//                                Closeable c = new ConvertingCursor<>(connection.hScan(redisSerializer.serialize("hmtest"), options), redisSerializer::deserialize);
//                                return c;
//                            }
//                        });
//
//        cursor1.forEachRemaining(System.out::println);
//        long t2 = System.currentTimeMillis();
//        System.out.println("查询用时: " + (t2 - t1) + "ms");
    }

}
