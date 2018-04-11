package org.spring.springboot.controller.file;

import org.spring.springboot.domain.ResponseBean;
import org.spring.springboot.service.FileService;
import org.spring.springboot.utils.FileUtils;
import org.spring.springboot.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping(value = "file")
public class FileManagerController {

    @Autowired
    FileService fileService;

    @Value("${filePath}") String filePath;
    /**
     * 上传文件到服务端
     * @param file
     * @return 上传成功，返回其url
     */
    @CrossOrigin(methods = {RequestMethod.OPTIONS,RequestMethod.POST})
    @RequestMapping(value = "/uploadFile", method = {RequestMethod.OPTIONS,RequestMethod.POST})
    public ResponseBean uploadFile(@RequestParam("file") MultipartFile file){
        ResponseBean responseBean = new ResponseBean();
        if (file.isEmpty()) {
            responseBean.setCode(ResponseBean.FAIL_CODE);
            responseBean.setMessage("上传失败，文件为空！");
            return responseBean;
        }
        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();
        System.out.println("fileName-->" + fileName);
        System.out.println("getContentType-->" + contentType);
        System.out.println("filePath-->" + filePath);

        try {
            responseBean = fileService.uploadFile(file.getBytes(), filePath, fileName);
        } catch (IOException e) {
            e.printStackTrace();
            responseBean.setMessage("上传错误！请重试！");
            responseBean.setCode(ResponseBean.FAIL_CODE);
            return responseBean;
        }

        return responseBean;
    }


}
