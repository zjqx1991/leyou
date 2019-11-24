package com.revanwang.ly.api.upload;

import com.revanwang.common.model.LYRevanResponse;
import com.revanwang.ly.domain.product.Brand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Api(value = "上传文件接口管理", description = "上传文件管理接口")
public interface IUploadAPI {

    @ApiOperation("上传图片")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "上传文件", required = true, dataType = "MultipartFile"),
    })
    LYRevanResponse uploadImage(MultipartFile file);
}
