package pers.xyj.modules.accountKeeper.controller.accountKeeper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.xyj.modules.accountKeeper.domain.ResponseResult;
import pers.xyj.modules.accountKeeper.domain.dto.PhoneDto;
import pers.xyj.modules.accountKeeper.domain.dto.PhoneLoginDto;
import pers.xyj.modules.accountKeeper.service.PhoneService;
import pers.xyj.modules.common.annotation.SystemLog;



@Api(value = "PhoneControllerApi", tags = {"短信操作接口"})
@RestController
@RequestMapping("/phone")
public class PhoneController {
    @Autowired
    private PhoneService phoneService;

//    @ApiOperation(value = "发送绑定手机验证码")
//    @SystemLog(businessName = "发送绑定手机验证码")
//    @GetMapping("/send/code/{phone}")
//    public ResponseResult sendPhoneCode(@PathVariable("phone") String phone) {
//        return phoneService.sendPhoneCode(phone);
//    }

    @ApiOperation(value = "发送手机登录验证码")
    @SystemLog(businessName = "发送手机登录验证码")
    @GetMapping("/send/code/login")
    public ResponseResult sendLoginPhoneCode(PhoneDto phoneDto) {
        return phoneService.sendLoginPhoneCode(phoneDto);
    }
}
