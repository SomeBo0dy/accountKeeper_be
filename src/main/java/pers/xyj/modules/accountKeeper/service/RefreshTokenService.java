package pers.xyj.modules.accountKeeper.service;

import pers.xyj.modules.accountKeeper.domain.ResponseResult;

public interface RefreshTokenService {
    ResponseResult refreshToken(String token);
}
