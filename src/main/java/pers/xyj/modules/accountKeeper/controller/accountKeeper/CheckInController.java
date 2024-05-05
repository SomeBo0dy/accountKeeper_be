package pers.xyj.modules.accountKeeper.controller.accountKeeper;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import pers.xyj.modules.accountKeeper.domain.ResponseResult;
import pers.xyj.modules.accountKeeper.service.CheckInService;
import org.springframework.web.bind.annotation.*;
import pers.xyj.modules.common.annotation.SystemLog;


@Api(value = "CheckInControllerApi", tags = {"签到操作接口"})
@RestController
@RequestMapping("checkIn")
public class CheckInController {
    @Autowired
    private CheckInService checkInService;

    @ApiOperation(value = "签到")
    @SystemLog(businessName = "签到")
    @GetMapping
    public ResponseResult checkIn() {
        return checkInService.checkIn();
    }

    @ApiOperation(value = "获取签到记录")
    @SystemLog(businessName = "获取签到记录")
    @GetMapping("/list")
    public ResponseResult getCheckInList() {
        return checkInService.getCheckInList();
    }

    @ApiOperation(value = "获取签到次数")
    @SystemLog(businessName = "获取签到次数")
    @GetMapping("/count")
    public ResponseResult getCheckInCount() {
        return checkInService.getCheckInCount();
    }
}

