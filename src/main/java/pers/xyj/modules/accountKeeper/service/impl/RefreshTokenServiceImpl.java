package pers.xyj.modules.accountKeeper.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.xyj.modules.accountKeeper.domain.ResponseResult;
import pers.xyj.modules.accountKeeper.domain.entity.LoginUser;
import pers.xyj.modules.accountKeeper.domain.vo.RefreshTokenVo;
import pers.xyj.modules.accountKeeper.service.RefreshTokenService;
import pers.xyj.modules.common.enums.AppHttpCodeEnum;
import pers.xyj.modules.common.exception.SystemException;
import pers.xyj.modules.common.utils.JwtUtil;
import pers.xyj.modules.common.utils.RedisCache;

import java.util.Objects;

import static pers.xyj.modules.common.constants.SystemConstants.*;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult refreshToken(String oldRefreshToken) {
        RefreshTokenVo refreshTokenVo = null;
        try {
            refreshTokenVo = refresh(oldRefreshToken);
        } catch (SystemException systemException){
            throw systemException;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        return ResponseResult.okResult(refreshTokenVo);
    }
    private RefreshTokenVo refresh(String oldRefreshToken) throws Throwable {
        //解析获取userId
        Claims claims = null;
        try {
            claims = JwtUtil.parseJWT(oldRefreshToken);
        } catch (ExpiredJwtException expiredJwtException){
            expiredJwtException.printStackTrace();
            Throwable ne = new SystemException(AppHttpCodeEnum.REFRESH_EXPIRED_NEED_LOGIN);
            ne.initCause(expiredJwtException);
            throw ne;
        } catch (Exception e) {
            e.printStackTrace();
            Throwable ne = new SystemException(AppHttpCodeEnum.NEED_LOGIN);
            ne.initCause(e);
            throw ne;
        }
        String userId = claims.getSubject();
        //从redis中中获取用户信息
        LoginUser loginUser = redisCache.getCacheObject(REFRESH_TOKEN + userId);
        //获取不到
        if (Objects.isNull(loginUser)){
            //说明登录过期
            throw new SystemException(AppHttpCodeEnum.NEED_LOGIN);
        }
        String newJwtToken = JwtUtil.createJWT(userId);
        String newRefreshJwtToken = JwtUtil.createJWT(userId , JwtUtil.REFRESH_JWT_TTL);
        //把用户信息存入redis
        redisCache.setCacheObject(ACCESS_TOKEN + userId, loginUser);
        redisCache.setCacheObject(REFRESH_TOKEN + userId, loginUser);
        return new RefreshTokenVo(newJwtToken, newRefreshJwtToken);
    }
}
