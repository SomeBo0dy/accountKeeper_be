package pers.xyj.modules.accountKeeper.service;


import pers.xyj.modules.accountKeeper.domain.ResponseResult;
import pers.xyj.modules.accountKeeper.domain.dto.PhoneDto;
import pers.xyj.modules.accountKeeper.domain.dto.PhoneLoginDto;

public interface PhoneService {
    public ResponseResult sendPhoneCode(String phone) ;

    ResponseResult sendLoginPhoneCode(PhoneDto phoneLoginDto);
}
