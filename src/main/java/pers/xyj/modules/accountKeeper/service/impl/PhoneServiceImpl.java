package pers.xyj.modules.accountKeeper.service.impl;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pers.xyj.modules.accountKeeper.domain.ResponseResult;
import pers.xyj.modules.accountKeeper.domain.dto.PhoneDto;
import pers.xyj.modules.accountKeeper.domain.dto.PhoneLoginDto;
import pers.xyj.modules.accountKeeper.service.PhoneService;
import pers.xyj.modules.common.enums.AppHttpCodeEnum;
import pers.xyj.modules.common.exception.SystemException;
import pers.xyj.modules.common.utils.PhoneUtils;
import pers.xyj.modules.common.utils.RandomUtils;
import pers.xyj.modules.common.utils.RedisCache;


import java.util.concurrent.TimeUnit;

import static pers.xyj.modules.common.constants.SystemConstants.CODE_TIMEOUT;
import static pers.xyj.modules.common.constants.SystemConstants.TYPE_MAP;


@Data
@Slf4j
@Service
public class PhoneServiceImpl implements PhoneService {

    @Autowired
    PhoneUtils phoneUtils;

    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult sendPhoneCode(String toPhone) {
        String code = redisCache.getCacheObject(toPhone);
        if (!StringUtils.hasText(code)) {
            redisCache.deleteObject(toPhone);
        }
        code = RandomUtils.getSixBitRandom();
        String[] templateParamSet = {code};
        phoneUtils.send(new String[]{toPhone}, "bind_code", templateParamSet);
        log.info("手机验证码已发送");
        redisCache.setCacheObject(toPhone, code, CODE_TIMEOUT, TimeUnit.SECONDS);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult sendLoginPhoneCode(PhoneDto phoneLoginDto) {
        String phone = phoneLoginDto.getPhone();
        String type = phoneLoginDto.getType();
        if (!StringUtils.hasText(phone)) {
            throw new SystemException(AppHttpCodeEnum.PHONE_NUMBER_NOT_NULL);
        }
        if (!StringUtils.hasText(type)) {
            throw new SystemException(AppHttpCodeEnum.TYPE_NOT_NULL);
        }
        if (!TYPE_MAP.keySet().contains(type)) {
            throw new SystemException(AppHttpCodeEnum.TYPE_ERROR);
        }
        String key = phone + "type:" + type;
        String code = redisCache.getCacheObject(key);
        //从redis中获取验证码,若已存在则删去该验证码
        if (!StringUtils.hasText(code)) {
            redisCache.deleteObject(key);
        }
        code = RandomUtils.getSixBitRandom();
        String[] templateParamSet = {code};
        phoneUtils.send(new String[]{phone}, "login_code", templateParamSet);
        log.info("手机验证码已发送");
        redisCache.setCacheObject(key, code, CODE_TIMEOUT, TimeUnit.SECONDS);
        return ResponseResult.okResult();
    }
}
