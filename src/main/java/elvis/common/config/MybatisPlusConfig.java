package elvis.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisPlusConfig {

    @Bean
    public EasySqlInjector easySqlInjector () {
        return new EasySqlInjector();
    }

}
