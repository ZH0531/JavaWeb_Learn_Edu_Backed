package com.zh8888.controller;

import com.zh8888.utils.AliyunOSSUtil;
import com.zh8888.pojo.dto.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Slf4j
@RestController
public class UploadController {
    @Autowired
    private AliyunOSSUtil aliyunOSSUtil;

    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        log.info("上传文件:{}", file.getOriginalFilename());

        String url = aliyunOSSUtil.upload(file.getBytes(), Objects.requireNonNull(file.getOriginalFilename()));
        log.info("文件上传成功，返回url:{}", url);

        return Result.success(url);
    }
}
