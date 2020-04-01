package ink.controller;

import ink.annotation.SystemLog;
import ink.model.SimpleResponse;
import ink.service.UpyunService;
import ink.util.Converter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
;
import java.util.List;

/**
 * @Author 桂乙侨
 * @Date 2020/3/10 21:52
 * @Version 1.0
 */
@RestController
@Slf4j
@RequestMapping("/api/files")
public class ImageController {

    @Autowired
    private UpyunService upyunService;

    /**
     * 上传文件，通过追加时间戳生成文件名，上传到upyun云存储
     * @param multipartFile
     * @return
     */
    @PostMapping
    public SimpleResponse upload(@NonNull @RequestParam("fileName") MultipartFile multipartFile)  {
        try {
            SimpleResponse simpleResponse = upyunService.uploadFile(Converter.Multipart2File(multipartFile));
            return simpleResponse;
        } catch (Exception e) {
            log.error("文件上传失败！"+e.getMessage());
            return new SimpleResponse(400,"文件上传失败了");
        }
    }

    /**
     * 通过 文件名 获取访问文件路径
     * @param fileName
     * @return
     */
    @GetMapping("{fileName}")
    public SimpleResponse find( @PathVariable("fileName") String fileName){
        try {
            final SimpleResponse response = upyunService.findFile(fileName);
            return response;
        } catch (Exception e) {
            log.error("文件路径获取失败"+e.getMessage());
            return new SimpleResponse(400,"文件路径获取失败，请检查路径！");
        }
    }
    /**
     * 查询所有文件的名称
     * @return
     */
    @GetMapping
    @SystemLog
    public SimpleResponse findAll(){
        try {
            final SimpleResponse response = upyunService.findFiles();
            return response;
        } catch (Exception e) {
            log.error("文件查询失败"+e.getMessage());
            return new SimpleResponse(400,"查询失败,服务器异常");
        }
    }

    /**
     * 根据文件名删除文件
     * @param fileName
     * @return
     */
    @DeleteMapping("/{fileName}")
    public SimpleResponse delete(@PathVariable String fileName){
        try {
            final SimpleResponse response = upyunService.deleteFile(fileName);
            return response;
        } catch (Exception e) {
            log.error("文件删除失败"+e.getMessage());
            return new SimpleResponse(200,"文件删除失败");
        }
    }
}
