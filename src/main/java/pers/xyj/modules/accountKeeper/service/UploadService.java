package pers.xyj.modules.accountKeeper.service;

import org.springframework.web.multipart.MultipartFile;
import pers.xyj.modules.accountKeeper.domain.ResponseResult;


public interface UploadService {
    ResponseResult uploadImg(MultipartFile img);
}
