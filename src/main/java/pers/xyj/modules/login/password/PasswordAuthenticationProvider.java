package pers.xyj.modules.login.password;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import pers.xyj.modules.common.utils.RedisCache;
import pers.xyj.modules.market.domain.entity.LoginUser;
import pers.xyj.modules.market.service.UserLoginService;

import java.util.Objects;

import static pers.xyj.modules.common.constants.SystemConstants.STATUS_NORMAL;

/**
 * 账号密码验证过滤器
 */
@Slf4j
@Component
public class PasswordAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private UserLoginService loginService;

    @Autowired
    private RedisCache redisCache;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (!supports(authentication.getClass())) {
            return null;
        }
        PasswordAuthenticationToken token = (PasswordAuthenticationToken) authentication;
        //获取邮箱
        String account = (String) authentication.getPrincipal();
        //获取验证码
        String password = (String) authentication.getCredentials();
        //获取登录类型
        String type = (String) token.getType();
        LoginUser loginUser = loginService.getByPasswordAndType(account, password, type);
        if (Objects.isNull(loginUser)) {
            throw new BadCredentialsException("账号或密码错误");
        }
        if (!STATUS_NORMAL.equals(loginUser.getUser().getState())) {
            throw new BadCredentialsException("账号已被锁定");
        }
        PasswordAuthenticationToken authenticationResult = new PasswordAuthenticationToken(loginUser, password, type, loginUser.getAuthorities());
        authenticationResult.setDetails(token.getDetails());
        return authenticationResult;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return PasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
