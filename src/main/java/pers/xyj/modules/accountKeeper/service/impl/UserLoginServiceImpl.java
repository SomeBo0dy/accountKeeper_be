package pers.xyj.modules.accountKeeper.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pers.xyj.modules.accountKeeper.domain.ResponseResult;
import pers.xyj.modules.accountKeeper.domain.dto.PasswordUserDto;
import pers.xyj.modules.accountKeeper.domain.entity.LoginUser;
import pers.xyj.modules.accountKeeper.domain.entity.User;
import pers.xyj.modules.accountKeeper.service.UserLoginService;
import pers.xyj.modules.accountKeeper.service.UserService;
import pers.xyj.modules.auth.domain.entity.UserRole;
import pers.xyj.modules.auth.mapper.MenuMapper;
import pers.xyj.modules.auth.mapper.UserRoleMapper;
import pers.xyj.modules.common.utils.BeanCopyUtils;
import pers.xyj.modules.common.utils.RedisCache;


import java.util.List;
import java.util.Objects;

import static pers.xyj.modules.common.constants.SystemConstants.*;

@Service
public class UserLoginServiceImpl implements UserLoginService {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public ResponseResult logout() {
        //获取token
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        //获取userId
        Long userId = loginUser.getUser().getId();
        //删除redis中的id
        redisCache.deleteObject("login:" + userId);
        return ResponseResult.okResult();
    }


    @Override
    public LoginUser getByPasswordAndType(String phoneNumber, String password, String type) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getPhoneNumber, phoneNumber);
        queryWrapper.eq(User::getType, type);
        User user = userService.getOne(queryWrapper);
        if (Objects.isNull(user)) {
            LambdaQueryWrapper<User> queryWrapper2 = new LambdaQueryWrapper<>();
            queryWrapper2.eq(User::getPhoneNumber, phoneNumber);
            queryWrapper2.eq(User::getType, type);
            user = userService.getOne(queryWrapper2);
        }
        //对比密码
        if (Objects.isNull(user) || !passwordEncoder.matches(password, user.getPassword())) {
            return null;
        }

        List<String> list = menuMapper.selectPermsByUserId(user.getId());
        return new LoginUser(user, list);
    }

    @Override
    public ResponseResult register(PasswordUserDto userRegisterDto) {
//        if (!StringUtils.hasText(userRegisterDto.getAccount())) {
//            throw new SystemException(AppHttpCodeEnum.ACCOUNT_NOT_NULL);
//        }
//        if (!StringUtils.hasText(userRegisterDto.getPassword())) {
//            throw new SystemException(AppHttpCodeEnum.PASSWORD_NOT_NULL);
//        }
//        if (!StringUtils.hasText(userRegisterDto.getType())) {
//            throw new SystemException(AppHttpCodeEnum.TYPE_NOT_NULL);
//        }
//        if (!StringUtils.hasText(userRegisterDto.getPhoneNumber())) {
//            throw new SystemException(AppHttpCodeEnum.PHONE_NUMBER_NOT_NULL);
//        }
//        if (!TYPE_MAP.keySet().contains(userRegisterDto.getType())) {
//            throw new SystemException(AppHttpCodeEnum.TYPE_ERROR);
//        }
//        if (!checkAccount(userRegisterDto)) {
//            throw new SystemException(AppHttpCodeEnum.ACCOUNT_EXIST);
//        }
//        checkCode(userRegisterDto);
//        if (!checkBind(userRegisterDto)) {
//            throw new SystemException(AppHttpCodeEnum.PHONE_BIND);
//        }
        //对密码加密
        String encodePassword = passwordEncoder.encode(userRegisterDto.getPassword());
        User user = BeanCopyUtils.copeBean(userRegisterDto, User.class);
        user.setPassword(encodePassword);
        //拼接账号作为用户名
        String nickName = TYPE_MAP.get(userRegisterDto.getType()) + "_" + userRegisterDto.getAccount();
        user.setNickName(nickName);
        //存入数据库
        userService.save(user);
        //配置角色表
        Long id = user.getId();
        if (userRegisterDto.getType().equals(IS_ADMIN)) {
            for (Long roleId : ROLE_MAP.values()) {
                UserRole userRole = new UserRole(id, roleId);
                userRoleMapper.insert(userRole);
            }
        } else {
            UserRole userRole = new UserRole(id, ROLE_MAP.get(userRegisterDto.getType()));
            userRoleMapper.insert(userRole);
        }
        return ResponseResult.okResult();
    }

    private boolean checkBind(PasswordUserDto register) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getPhoneNumber, register.getPhoneNumber());
        queryWrapper.eq(User::getType, register.getType());
        User one = userService.getOne(queryWrapper);
        if (Objects.isNull(one)) {
            return true;
        }
        return false;
    }

    @Override
    public LoginUser getByPhoneAndType(String phone, String type) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getPhoneNumber, phone);
        queryWrapper.eq(User::getType, type);
        User user = userService.getOne(queryWrapper);
        if (Objects.isNull(user)) {
            return null;
        }
        List<String> list = menuMapper.selectPermsByUserId(user.getId());
        return new LoginUser(user, list);
    }

    private boolean checkAccount(PasswordUserDto userRegisterDto) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getPhoneNumber, userRegisterDto.getPhoneNumber());
        queryWrapper.eq(User::getType, userRegisterDto.getType());
        User user = userService.getOne(queryWrapper);
        if (Objects.isNull(user)) {
            return true;
        }
        return false;
    }

//    private void checkCode(PasswordUserDto register) {
//        String phone = register.getPhoneNumber();
//        String code = register.getCode();
//
//        String curCode = redisCache.getCacheObject(phone);
//        if (!StringUtils.hasText(curCode)) {
//            throw new BadCredentialsException("验证码已过期，请重新发送验证码");
//        }
//        if (!curCode.equals(code)) {
//            throw new BadCredentialsException("验证码错误");
//        }
//    }
}
