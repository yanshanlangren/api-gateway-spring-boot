package mysql;

import elvis.bo.User;
import elvis.dao.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@SpringBootTest(classes = elvis.App.class)
public class MysqlTest {
    @Autowired
    UserMapper userMapper;

    @Test
    public void batchInsert() {
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 1 * 10; i++) {
            User user = new User();
            user.setAge((int) (100 * Math.random()));
            user.setName(UUID.randomUUID().toString());
            list.add(user);
        }
        long t0 = System.currentTimeMillis();
        for (User user : list) {
            userMapper.insert(user);
        }
//        userMapper.insertBatchSomeColumn(list);
        System.out.println(System.currentTimeMillis()-t0+" ms cost");
    }

}
