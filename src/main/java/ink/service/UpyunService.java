package ink.service;

import com.upyun.UpException;
import ink.model.SimpleResponse;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @Author 桂乙侨
 * @Date 2020/4/1 14:20
 * @Version 1.0
 */
public interface UpyunService {
    SimpleResponse uploadFile(File file)throws IOException, UpException;

    SimpleResponse findFile (String fileName)throws IOException, UpException;

    SimpleResponse findFiles()throws IOException, UpException;

    SimpleResponse deleteFile(String fileName)throws IOException, UpException;
}
