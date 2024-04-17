package pers.xyj.modules.accountKeeper.controller.accountKeeper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pers.xyj.modules.accountKeeper.domain.ResponseResult;
import pers.xyj.modules.accountKeeper.domain.dto.PhoneLoginDto;
import pers.xyj.modules.accountKeeper.service.UserLoginService;
import pers.xyj.modules.common.annotation.SystemLog;


@Api(value = "UserLoginControllerApi", tags = {"登录注册操作接口"})
@RestController
public class UserLoginController {

    @Autowired
    private UserLoginService userloginService;

    @ApiOperation(value = "用户手机验证码登录")
    @SystemLog(businessName = "用户手机验证码登录")
    @PostMapping("/login/phone")
    public ResponseResult loginPassword(@RequestBody PhoneLoginDto userLoginDto) {
        return ResponseResult.okResult();
    }

    @ApiOperation(value = "用户退出登录")
    @SystemLog(businessName = "用户退出登录")
    @PostMapping("/logout")
    public ResponseResult logout() {
        return userloginService.logout();
    }

//    @ApiOperation(value = "用户注册")
//    @SystemLog(businessName = "用户注册")
//    @PostMapping("/register")
//    public ResponseResult register(@RequestBody PasswordUserDto userRegisterDto) {
//        return userloginService.register(userRegisterDto);
//    }

}
