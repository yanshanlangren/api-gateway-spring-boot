package elvis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("elvis.dao.mapper")
@EnableScheduling
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }
}
