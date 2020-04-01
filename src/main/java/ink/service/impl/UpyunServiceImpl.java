package ink.service.impl;

import com.upyun.RestManager;
import com.upyun.UpException;
import ink.model.SimpleResponse;
import ink.property.UpyunProperties;
import ink.service.UpyunService;
import ink.util.UpyunUtils;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author 桂乙侨
 * @Date 2020/3/11 9:56
 * @Version 1.0
 */
@Service
@Slf4j
public class UpyunServiceImpl implements UpyunService {

    @Autowired
    private RestManager restManager;
    @Autowired
    private UpyunProperties upyunProperties;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    public SimpleResponse uploadFile(File file) throws IOException, UpException {
        if(restManager.readDirIter("/",null).body().string().equals("")){
            restManager.mkDir(upyunProperties.getPath());
        }
        final Response response = restManager.writeFile(upyunProperties.getPath() + file.getName(), file, null);
        if(response.code() == 200){
            log.info("添加文件成功");
            return new SimpleResponse(200,"文件上传成功");
        }else{
            log.error("添加文件失败");
            return new SimpleResponse(400,"添加文件失败");
        }
    }

    public SimpleResponse findFile(String fileName) throws IOException, UpException {
        String address = redisTemplate.boundValueOps(fileName).get();
        if( address !=null ){
            return new SimpleResponse<String>(200,"查询成功",address);
        };
        String upt = upyunProperties.getPath()+fileName;
        final Response response = restManager.readFile(upyunProperties.getPath() + fileName);
        if(response.code() != 200){
            log.error("文件失败");
            return new SimpleResponse(400,"文件不存在");
        }
        address = UpyunUtils.getUpt(upt);
        redisTemplate.boundValueOps(fileName).set(address,1, TimeUnit.MINUTES);
        return  new SimpleResponse<String>(200,"查询成功",address);
    }

    public SimpleResponse findFiles() throws IOException, UpException {
        Response response = restManager.readDirIter(upyunProperties.getPath(),null);
        List<String> list = UpyunUtils.string2List(response.body().string());
        return new SimpleResponse<List<String>>(200,"文件名获取成功",list);
}

    @Override
    public SimpleResponse deleteFile(String fileName) throws IOException, UpException {
        final Response response = restManager.deleteFile(upyunProperties.getPath() + fileName, null);
        if(response.isSuccessful()){
            log.info("文件删除成功");  return new SimpleResponse<List<String>>(200,"文件删除成功");
        }else{
            log.error("文件删除失败");
            return new SimpleResponse(400,"删除异常，文件可能不存在");
        }
    }

}
