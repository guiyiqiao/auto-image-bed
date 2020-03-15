package ink.controller;

import ink.service.UpyunService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;;
import java.util.List;
import java.util.UUID;

/**
 * @Author 桂乙侨
 * @Date 2020/3/10 21:52
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/files")
public class ImageController {

    @Autowired
    private UpyunService upyunService;

    /**
     * 上传文件，通过UUID随机生成文件名，上传到upyun
     * @param multipartFile
     * @return
     */
    @PostMapping
    public String uploadImage(@NonNull @RequestParam("fileName") MultipartFile multipartFile)  {
        try {
        String fileName = multipartFile.getOriginalFilename();
        String prefix=fileName.substring(fileName.lastIndexOf("."));
        String newFileName = UUID.randomUUID().toString();

        File file = File.createTempFile(newFileName,prefix);
        newFileName = newFileName+prefix;
        multipartFile.transferTo(file);
        upyunService.uploadImage(file);
        return "上传成功，文件名开头为"+newFileName;
        } catch (Exception e) {
            e.printStackTrace();
            return "上传失败";
        }
    }

    /**
     * 通过 文件名 获取访问文件路径
     * @param fileName
     * @return
     */
    @GetMapping("{fileName}")
    public String findImage( @PathVariable("fileName") String fileName){
        try {
            return upyunService.findImage(fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return "查找地址失败，请重试！";
        }
    }

    /**
     * 查询所有文件的名称
     * @return
     */
    @GetMapping
    public List<String> findImages(){
        try {
            List<String> fileNames = upyunService.findImages();
            return fileNames;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
