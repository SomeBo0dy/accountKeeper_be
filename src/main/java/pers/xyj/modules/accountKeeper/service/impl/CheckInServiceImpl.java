package pers.xyj.modules.accountKeeper.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import pers.xyj.modules.accountKeeper.domain.ResponseResult;
import pers.xyj.modules.accountKeeper.mapper.CheckInMapper;
import pers.xyj.modules.accountKeeper.domain.entity.CheckIn;
import pers.xyj.modules.accountKeeper.service.CheckInService;
import org.springframework.stereotype.Service;
import pers.xyj.modules.common.enums.AppHttpCodeEnum;
import pers.xyj.modules.common.exception.SystemException;
import pers.xyj.modules.common.utils.SecurityUtils;

import java.util.List;

@Slf4j
@Service
public class CheckInServiceImpl extends ServiceImpl<CheckInMapper, CheckIn> implements CheckInService {

    @Autowired
    private CheckInMapper checkInMapper;
    @Override
    public ResponseResult checkIn() {
        Long userId = SecurityUtils.getUserId();
        CheckIn checkIn = new CheckIn();
        checkIn.setUId(userId);
        int insert = checkInMapper.insert(checkIn);
        if (insert!=1){
            throw new SystemException(AppHttpCodeEnum.ERROR);
        }
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getCheckInList() {
        LambdaQueryWrapper<CheckIn> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CheckIn::getUId, SecurityUtils.getUserId());
        List<CheckIn> checkIns = checkInMapper.selectList(queryWrapper);
        return ResponseResult.okResult(checkIns);
    }

    @Override
    public ResponseResult getCheckInCount() {
        LambdaQueryWrapper<CheckIn> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CheckIn::getUId, SecurityUtils.getUserId());
        Integer count = checkInMapper.selectCount(queryWrapper);
        return ResponseResult.okResult(count);
    }
}

