package org.spring.springboot.service;

import org.spring.springboot.domain.ResponseBean;

public interface FileService {
    //上传文件到服务器
    ResponseBean uploadFile(byte[] file, String filePath, String fileName);
}
