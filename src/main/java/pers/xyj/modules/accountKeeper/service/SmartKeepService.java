package pers.xyj.modules.accountKeeper.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pers.xyj.modules.accountKeeper.domain.ResponseResult;


public interface SmartKeepService {
    ResponseResult smartKeepByShoppingReceipt(MultipartFile receipt);
}
