package pers.xyj.modules.accountKeeper.service.impl;

import org.springframework.stereotype.Service;
import pers.xyj.modules.accountKeeper.domain.ResponseResult;
import pers.xyj.modules.accountKeeper.domain.vo.RefreshTokenVo;
import pers.xyj.modules.accountKeeper.service.RefreshTokenService;
import pers.xyj.modules.common.exception.SystemException;
import pers.xyj.modules.common.utils.JwtUtil;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    @Override
    public ResponseResult refreshToken(String oldRefreshToken) {
        RefreshTokenVo refreshTokenVo = null;
        try {
            refreshTokenVo = JwtUtil.refreshToken(oldRefreshToken);
        } catch (SystemException systemException){
            throw systemException;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        return ResponseResult.okResult(refreshTokenVo);
    }
}
