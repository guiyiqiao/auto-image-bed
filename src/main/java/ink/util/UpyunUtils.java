package ink.util;

import com.upyun.UpYunUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author 桂乙侨
 * @Date 2020/3/11 10:32
 * @Version 1.0
 */
public class UpyunUtils {
    public static final String bucket = "bucket";
    public static final String userName = "userName";
    public static final String password = "password";
    public static final String secret = "secret";
    public static final String path = "/path/";

    public static List<String> string2List( String str){
        List<String> strings = new ArrayList<>();
        String[] strs = str.split("\\s+");
        for(int i =0; i < strs.length;i+=4){
            strings.add(strs[i]);
        }
        return strings;
    }
    public static String getUpt(String uri){
        String etime = getTimeStamp();
        //有效时间为10分钟
        String s =secret+"&"+etime+"&"+uri;
        String sign = UpYunUtils.md5(s);
        String token = sign.substring(12,20)+etime;
        return "_upt="+token;
    }
    private static String getTimeStamp() {
        long time = System.currentTimeMillis();
        String nowTimeStamp = String.valueOf((time / 1000)+600);
        return nowTimeStamp;
    }
}


