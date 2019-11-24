package com.revanwang.service;

import com.revanwang.common.model.LYRevanResponse;
import org.springframework.web.multipart.MultipartFile;

public interface IUploadService {

    /**
     * 上传图片
     * @param file 文件图片
     * @return
     */
    LYRevanResponse uploadImage(MultipartFile file);
}
