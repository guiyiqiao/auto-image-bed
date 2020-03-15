package ink.service;

import com.upyun.RestManager;
import com.upyun.UpException;
import ink.util.UpyunUtils;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @Author 桂乙侨
 * @Date 2020/3/11 9:56
 * @Version 1.0
 */
@Service
public class UpyunService {

    @Autowired
    private RestManager restManager;

    public void uploadImage(File file) throws IOException, UpException {
        restManager.writeFile(UpyunUtils.path +file.getName(),file,null);
    }

    public String findImage(String fileName) throws IOException, UpException {
        String uri = "/img/"+fileName;
        return  "http://yqgui-image.test.upcdn.net/img/"+fileName+"?"+UpyunUtils.getUpt(uri);
    }

    public List<String> findImages() throws IOException, UpException {
        Response response = restManager.readDirIter(UpyunUtils.path,null);
        return  UpyunUtils.string2List(response.body().string());
    }

}
