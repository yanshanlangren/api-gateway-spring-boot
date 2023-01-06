package elvis.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TestScheduler {

    //每五秒执行一次。
//    @Scheduled(cron = "0/5 * * * * ?")
    @Scheduled(fixedRate = 2000L, initialDelay = 20000L)
    public void sayHello2() {
        System.out.println(LocalDateTime.now());
    }
}
