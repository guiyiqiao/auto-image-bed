package ink.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @Author 桂乙侨
 * @Date 2020/4/1 14:37
 * @Version 1.0
 */
public class Converter {

    public static File Multipart2File(MultipartFile multipartFile) throws IOException {
        String fileName = multipartFile.getOriginalFilename();
        String prefix=fileName.substring(fileName.lastIndexOf("."));
        String newFileName = fileName.substring(0,fileName.lastIndexOf("."))+System.currentTimeMillis();
        File file = File.createTempFile(newFileName,prefix);
        newFileName = newFileName+prefix;
        multipartFile.transferTo(file);
        return file;
    }

}
