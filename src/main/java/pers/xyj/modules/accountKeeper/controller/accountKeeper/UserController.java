package pers.xyj.modules.accountKeeper.controller.accountKeeper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pers.xyj.modules.accountKeeper.domain.ResponseResult;
import pers.xyj.modules.accountKeeper.domain.dto.PasswordDto;
import pers.xyj.modules.accountKeeper.domain.dto.UserInfoDto;
import pers.xyj.modules.accountKeeper.service.UserService;
import pers.xyj.modules.common.annotation.SystemLog;

@Api(value = "UserControllerApi", tags = {"用户操作接口(除业务以外的公共接口)"})
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @ApiOperation(value = "改密码")
    @SystemLog(businessName = "改密码")
    @PutMapping("/password")
    @PreAuthorize("hasAuthority('user:info:update')")
    public ResponseResult editPassword(@RequestBody PasswordDto passwordDto) {
        return userService.editPassword(passwordDto);
    }

    @ApiOperation(value = "更新用户信息")
    @SystemLog(businessName = "更新用户信息")
    @PutMapping("/info")
    @PreAuthorize("hasAuthority('user:info:update')")
    public ResponseResult editUserInfo(@RequestBody UserInfoDto userInfoDto) {
        return userService.editUserInfo(userInfoDto);
    }

    @ApiOperation(value = "获取用户个人信息")
    @SystemLog(businessName = "获取用户个人信息")
    @GetMapping("/info")
    @PreAuthorize("hasAuthority('user:info:list')")
    public ResponseResult getUserInfo() {
        return userService.getUserInfo();
    }

    @ApiOperation(value = "用户注销")
    @SystemLog(businessName = "用户注销")
    @DeleteMapping
    @PreAuthorize("hasAuthority('user:account:delete')")
    public ResponseResult cancelAccount() {
        return userService.cancelAccount();
    }


}
