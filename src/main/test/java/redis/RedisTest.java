package redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@SpringBootTest(classes = elvis.App.class)
public class RedisTest {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    void contextLoads() {
        redisTemplate.opsForValue().set("name", "张三");
        redisTemplate.opsForValue().set("k3","v3");
        System.out.println(redisTemplate.opsForValue().get("name"));
        redisTemplate.expire("k3", 60, TimeUnit.SECONDS);
        System.out.println(redisTemplate.getExpire("k3"));
        //获取所有的key
        Set<String> keys = redisTemplate.keys("*");
        System.out.println(keys);
    }

    @Test
    void testList(){
        System.out.println(redisTemplate.opsForList().size("mylist"));
    }

    @Test
    void testString() {
        ValueOperations<String, String> forValue = redisTemplate.opsForValue();//操作字符串类型
        forValue.set("k1","v1");
        System.out.println(forValue.get("k1"));

        Map<String,String> map=new HashMap<>();
        map.put("k9","v9");
        map.put("k10","v10");
        forValue.multiSet(map);
    }
}
