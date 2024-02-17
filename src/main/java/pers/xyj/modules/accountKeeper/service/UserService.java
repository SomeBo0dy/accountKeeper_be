package pers.xyj.modules.accountKeeper.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.xyj.modules.accountKeeper.domain.ResponseResult;
import pers.xyj.modules.accountKeeper.domain.dto.PasswordDto;
import pers.xyj.modules.accountKeeper.domain.dto.SelectUserPageDto;
import pers.xyj.modules.accountKeeper.domain.dto.StateDto;
import pers.xyj.modules.accountKeeper.domain.dto.UserInfoDto;
import pers.xyj.modules.accountKeeper.domain.entity.User;


/**
 * 用户表(User)表服务接口
 */
public interface UserService extends IService<User> {


    ResponseResult editUserInfo(UserInfoDto userInfoDto);

    ResponseResult editPassword(PasswordDto passwordDto);

    ResponseResult getUserInfo();

    ResponseResult getUserInfoById(Long id);

    ResponseResult getUserInfoByPage(SelectUserPageDto userPageDto);

    ResponseResult setUserState(StateDto stateDto);

    ResponseResult cancelAccount();

    ResponseResult sysCancelAccount(Long userId);

}
