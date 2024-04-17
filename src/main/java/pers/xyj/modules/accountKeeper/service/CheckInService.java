package pers.xyj.modules.accountKeeper.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.xyj.modules.accountKeeper.domain.ResponseResult;
import pers.xyj.modules.accountKeeper.domain.entity.CheckIn;


public interface CheckInService extends IService<CheckIn> {

    ResponseResult checkIn();

    ResponseResult getCheckInList();

    ResponseResult getCheckInCount();
}

