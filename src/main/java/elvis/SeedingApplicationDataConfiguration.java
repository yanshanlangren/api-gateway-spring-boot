package elvis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import elvis.bo.Domain;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class SeedingApplicationDataConfiguration {

    @Bean
    public Map<String, Domain> perDomain(){
        final Map<String, Domain> domainMap = new HashMap<>();
        domainMap.put("ad-001", new Domain("ad-001", "test-name", "description"));
        return domainMap;
    }

    @Bean
    @Primary
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return objectMapper;
    }
}
