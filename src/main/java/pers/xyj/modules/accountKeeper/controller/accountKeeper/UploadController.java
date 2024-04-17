package pers.xyj.modules.accountKeeper.controller.accountKeeper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pers.xyj.modules.accountKeeper.domain.ResponseResult;
import pers.xyj.modules.accountKeeper.service.UploadService;


@Api(value = "UploadControllerApi",tags={"上传文件操作接口"})
@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @ApiOperation(value="上传图片")
    @PostMapping("/img")
    public ResponseResult uploadImg(MultipartFile img){
        return uploadService.uploadImg(img);
    }

}

