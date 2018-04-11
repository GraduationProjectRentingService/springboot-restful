package org.spring.springboot.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.spring.springboot.dao.FileDao;
import org.spring.springboot.domain.MyFile;
import org.spring.springboot.domain.ResponseBean;
import org.spring.springboot.service.FileService;
import org.spring.springboot.utils.FileUtils;
import org.spring.springboot.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class FileServiceImpl implements FileService {

    @Value("${baseFileUrl}") String baseFileUrl;

    @Autowired
    FileDao fileDao;

    @Override
    public ResponseBean uploadFile(byte[] file, String filePath, String fileName) {
        ResponseBean responseBean = new ResponseBean();
        try {
            MyFile myFile = new MyFile();
            myFile.setFileName(fileName);//保存加密前的文件名
            String fileType = fileName.substring(fileName.lastIndexOf('.'), fileName.length());
            System.out.println("fileType: " + fileType);
            String md5FileName = SecurityUtils.MD5(fileName + System.currentTimeMillis()).toLowerCase() + fileType;
            myFile.setFileUrl(baseFileUrl + md5FileName);
            FileUtils.uploadFile(file, filePath, md5FileName);

            fileDao.saveFile(myFile);//将文件信息保存到数据库中

            responseBean.setCode(ResponseBean.SUCCESS_CODE);
            responseBean.setMessage("上传成功！");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("fileUrl", myFile.getFileUrl());
            responseBean.setContent(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
            responseBean.setMessage("上传错误！请重试！");
            responseBean.setCode(ResponseBean.FAIL_CODE);
            return responseBean;
        }
        return responseBean;
    }
}
