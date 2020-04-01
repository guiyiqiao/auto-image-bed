package ink.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @Author 桂乙侨
 * @Date 2020/4/1 11:30
 * @Version 1.0
 */
@ConfigurationProperties(prefix = "upyun")
@Configuration
@Component
@Data
public class UpyunProperties {
    /**
     *
     *     bucket; 服务名
     *     url; 域名
     *     user_name; 用户名
     *     password; 密码
     *     secret; 密钥
     *     path = "/file/";默认文件上传路径
     *     expire_seconds = 60*10; token 默认过期时间10分钟
     */
    private String bucket_name;
    private String url;
    private String user_name;
    private String password;
    private String secret;
    private String path = "/file/";
    private Integer expire_seconds = 600;

}
