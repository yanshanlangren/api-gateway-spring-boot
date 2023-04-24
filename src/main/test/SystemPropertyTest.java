import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.TimeUnit;

@SpringBootTest(classes = elvis.App.class)
public class SystemPropertyTest {
    @Value("${os.name}")
    public String prop;

    @Value("#{server.port}")
    public String port;

    @Test
    public void propertyTest() {
        System.out.println(port);
    }

}
