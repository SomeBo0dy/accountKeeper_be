package pers.xyj.modules.login.phone;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import pers.xyj.modules.accountKeeper.domain.entity.Book;
import pers.xyj.modules.accountKeeper.domain.entity.BookUser;
import pers.xyj.modules.accountKeeper.domain.entity.LoginUser;
import pers.xyj.modules.accountKeeper.domain.entity.User;
import pers.xyj.modules.accountKeeper.mapper.BookMapper;
import pers.xyj.modules.accountKeeper.mapper.BookUserMapper;
import pers.xyj.modules.accountKeeper.service.UserLoginService;
import pers.xyj.modules.accountKeeper.service.UserService;
import pers.xyj.modules.auth.domain.entity.UserRole;
import pers.xyj.modules.auth.mapper.UserRoleMapper;
import pers.xyj.modules.common.utils.BeanCopyUtils;
import pers.xyj.modules.common.utils.RedisCache;


import java.util.Objects;

import static pers.xyj.modules.common.constants.SystemConstants.*;
import static pers.xyj.modules.common.constants.SystemConstants.ROLE_MAP;


/**
 * 手机验证过滤器
 */
@Slf4j
@Component
public class PhoneCodeAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserLoginService loginService;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserService userService;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private BookUserMapper bookUserMapper;

    @Override
    @Transactional
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (!supports(authentication.getClass())) {
            return null;
        }
        PhoneCodeAuthenticationToken token = (PhoneCodeAuthenticationToken) authentication;
        //获取手机号
        String phone = (String) authentication.getPrincipal();
        //获取验证码
        String p_code = (String) authentication.getCredentials();
        //获取登录类型
        String type = (String) token.getType();
        //验证验证码
        String key = phone + "type:" + type;
        String curCode = redisCache.getCacheObject(key);

        if (!StringUtils.hasText(curCode)) {
            throw new BadCredentialsException("验证码已过期，请重新发送验证码");
        }
        if (!curCode.equals(p_code)) {
            throw new BadCredentialsException("验证码错误");
        }
        LoginUser loginUser = loginService.getByPhoneAndType(phone, type);
        if (Objects.isNull(loginUser)) {
            //对密码加密
            String encodePassword = passwordEncoder.encode("12345678");
            User user = new User();
            user.setPhoneNumber(phone);
            user.setPassword(encodePassword);
            //拼接账号作为用户名
            String nickName = TYPE_MAP.get(IS_USER) + "_" + phone;
            user.setNickName(nickName);
            //存入数据库
            userService.save(user);
            //配置角色表
            Long id = user.getId();
            UserRole userRole = new UserRole(id, ROLE_MAP.get(IS_USER));
            userRoleMapper.insert(userRole);
            loginUser = loginService.getByPhoneAndType(phone, type);
            //创建默认账本
            Book book = new Book();
            book.setName("默认账本");
            book.setDescription("默认使用的账本");
            bookMapper.insert(book);
            //创建表和人联系
            BookUser bookUser = new BookUser();
            bookUser.setBId(book.getId());
            bookUser.setUId(id);
            bookUser.setPriority(1);
            bookUserMapper.insert(bookUser);
        }
        if (!STATUS_NORMAL.equals(loginUser.getUser().getState())) {
            throw new BadCredentialsException("账号已被锁定");
        }
        //登录成功后，验证码失效
        redisCache.deleteObject(key);
        PhoneCodeAuthenticationToken authenticationResult = new PhoneCodeAuthenticationToken(loginUser, p_code, type, loginUser.getAuthorities());
        authenticationResult.setDetails(token.getDetails());
        return authenticationResult;
    }


    //断是上面 authenticate 方法的 authentication 参数，是哪种类型
    @Override
    public boolean supports(Class<?> authentication) {
        return PhoneCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }
}