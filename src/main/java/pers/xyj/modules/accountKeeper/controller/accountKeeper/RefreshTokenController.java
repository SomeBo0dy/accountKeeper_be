package pers.xyj.modules.accountKeeper.controller.accountKeeper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.xyj.modules.accountKeeper.domain.ResponseResult;
import pers.xyj.modules.accountKeeper.service.RefreshTokenService;
import pers.xyj.modules.common.annotation.SystemLog;

import java.util.Date;

@Api(value = "RefreshTokenControllerApi", tags = {"刷新token操作接口"})
@RestController
@RequestMapping("token/refresh")
public class RefreshTokenController {

    @Autowired
    private RefreshTokenService refreshTokenService;

    @ApiOperation(value="获取新的token")
    @SystemLog(businessName = "获取新的token")
    @GetMapping
    public ResponseResult refreshToken(String oldRefreshToken){
        return refreshTokenService.refreshToken(oldRefreshToken);
    }

}
