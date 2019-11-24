package com.revanwang.web.controller;


import com.revanwang.common.model.LYRevanResponse;
import com.revanwang.ly.api.upload.IUploadAPI;
import com.revanwang.service.IUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("upload")
public class UploadController implements IUploadAPI {

    @Autowired
    private IUploadService uploadService;

    @PostMapping("image")
    public LYRevanResponse uploadImage(@RequestParam("file")MultipartFile file) {
        return this.uploadService.uploadImage(file);
    }
}
