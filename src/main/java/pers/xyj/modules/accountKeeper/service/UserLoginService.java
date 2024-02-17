package pers.xyj.modules.accountKeeper.service;


import pers.xyj.modules.accountKeeper.domain.ResponseResult;
import pers.xyj.modules.accountKeeper.domain.dto.PasswordUserDto;
import pers.xyj.modules.accountKeeper.domain.entity.LoginUser;

public interface UserLoginService {
    ResponseResult logout();


    LoginUser getByPasswordAndType(String account, String password, String type);

    ResponseResult register(PasswordUserDto userRegisterDto);

    LoginUser getByPhoneAndType(String email, String type);
}
