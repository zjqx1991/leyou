package com.revanwang.service.impl;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.revanwang.common.exception.RevanThrowException;
import com.revanwang.common.model.LYRevanResponse;
import com.revanwang.common.model.RevanResponseCode;
import com.revanwang.common.model.RevanResponseData;
import com.revanwang.config.UploadProperties;
import com.revanwang.service.IUploadService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Service
@EnableConfigurationProperties(UploadProperties.class)
public class UploadServiceImpl implements IUploadService {

    @Autowired
    private UploadProperties properties;
    @Autowired
    private FastFileStorageClient storageClient;

    @Override
    public LYRevanResponse uploadImage(MultipartFile file) {
        try {
            //1、校验文件类型
            String contentType = file.getContentType();
            if (!properties.getAllowTypes().contains(contentType)) {
                RevanThrowException.throwException(RevanResponseCode.INVALID_FILE_TYPE);
            }
            //2、校验文件内容
            BufferedImage image = ImageIO.read(file.getInputStream());
            if (image == null) {
                RevanThrowException.throwException(RevanResponseCode.INVALID_FILE_TYPE);
            }

            //3、将图片上传到FastDFS
            //3.1、获取文件后缀名
            String extension = StringUtils.substringAfterLast(file.getOriginalFilename(), ".");
            //3.2、长传
            StorePath storePath = this.storageClient.uploadFile(file.getInputStream(), file.getSize(), extension, null);
            RevanResponseData<String> data = new RevanResponseData<>();
            data.setData(properties.getBaseURL() + storePath.getFullPath());
            return new LYRevanResponse(RevanResponseCode.SUCCESS, data);

        } catch (IOException e) {
            e.printStackTrace();
            RevanThrowException.throwException(RevanResponseCode.UPLOAD_FILE_FAIL);
        }
        return new LYRevanResponse(RevanResponseCode.UPLOAD_FILE_FAIL);
    }
}
