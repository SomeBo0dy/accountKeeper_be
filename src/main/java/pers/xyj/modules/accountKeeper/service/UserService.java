package pers.xyj.modules.accountKeeper.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;
import pers.xyj.modules.accountKeeper.domain.ResponseResult;
import pers.xyj.modules.accountKeeper.domain.dto.*;
import pers.xyj.modules.accountKeeper.domain.entity.User;


/**
 * 用户表(User)表服务接口
 */
public interface UserService extends IService<User> {


    ResponseResult editUserInfo(MultipartFile avatarFile, String nickName, String introduction);

    ResponseResult editPassword(PasswordDto passwordDto);

    ResponseResult getUserInfo();

    ResponseResult getUserInfoById(Long id);

    ResponseResult getUserInfoByPage(SelectUserPageDto userPageDto);

    ResponseResult setUserState(StateDto stateDto);

    ResponseResult cancelAccount();

    ResponseResult sysCancelAccount(Long userId);

    ResponseResult editUserInfoString(EditUserInfoDto editUserInfoDto);
}
