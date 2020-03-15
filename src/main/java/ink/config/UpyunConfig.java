package ink.config;

import com.upyun.RestManager;
import com.upyun.UpYunUtils;
import ink.util.UpyunUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author 桂乙侨
 * @Date 2020/3/10 23:04
 * @Version 1.0
 */
@Configuration
public class UpyunConfig {
    @Bean
    public RestManager getRestManager(){
        RestManager restManager = new RestManager(UpyunUtils.bucket,UpyunUtils.userName,UpyunUtils.password);
        return restManager;
    }
}
