package ink.config;

import com.upyun.RestManager;
import com.upyun.UpException;
import com.upyun.UpYunUtils;
import ink.property.UpyunProperties;
import ink.util.UpyunUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Author 桂乙侨
 * @Date 2020/3/10 23:04
 * @Version 1.0
 */
@Configuration
@Slf4j
@Component
public class UpyunConfig {

    @Autowired
    private UpyunProperties upyunProperties;

    @Bean
    public RestManager getRestManager() throws IOException, UpException {
        RestManager restManager = new RestManager(upyunProperties.getBucket_name(),upyunProperties.getUser_name(),upyunProperties.getPassword());
        return restManager;
    }
}
