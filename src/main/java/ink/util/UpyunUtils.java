package ink.util;

import com.upyun.UpYunUtils;
import ink.property.UpyunProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author 桂乙侨
 * @Date 2020/3/11 10:32
 * @Version 1.0
 */
@Component
public class UpyunUtils {
    private  static UpyunProperties upyunProperties;

    @Autowired
    private UpyunProperties properties;

    @PostConstruct
    public void getUpyunProperties(){
        upyunProperties = properties;
    }

    public static List<String> string2List(String str){
        List<String> strings = new ArrayList<>();
        String[] strs = str.split("\\s+");
        for(int i =0; i < strs.length;i+=4){
            strings.add(strs[i]);
        }
        return strings;
    }
    public static String getUpt(String uri){
        String etime = getTimeStamp();
        String s =upyunProperties.getSecret()+"&"+etime+"&"+uri;
        String sign = UpYunUtils.md5(s);
        String token = sign.substring(12,20)+etime;
        return upyunProperties.getUrl()+uri+"?"+"_upt="+token;
    }
    private static String getTimeStamp() {
        long time = System.currentTimeMillis();
        String nowTimeStamp = String.valueOf((time / 1000)+upyunProperties.getExpire_seconds());
        return nowTimeStamp;
    }
}


