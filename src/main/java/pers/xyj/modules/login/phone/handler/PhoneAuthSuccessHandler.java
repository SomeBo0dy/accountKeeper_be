package pers.xyj.modules.login.phone.handler;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import pers.xyj.modules.accountKeeper.domain.ResponseResult;
import pers.xyj.modules.accountKeeper.domain.entity.LoginUser;
import pers.xyj.modules.accountKeeper.domain.vo.UserInfoVo;
import pers.xyj.modules.accountKeeper.domain.vo.UserLoginVo;
import pers.xyj.modules.auth.mapper.MenuMapper;
import pers.xyj.modules.auth.mapper.RoleMapper;
import pers.xyj.modules.common.utils.BeanCopyUtils;
import pers.xyj.modules.common.utils.JwtUtil;
import pers.xyj.modules.common.utils.RedisCache;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static pers.xyj.modules.common.constants.SystemConstants.*;
import static pers.xyj.modules.common.utils.JwtUtil.REFRESH_JWT_TTL;


@Slf4j
@Component
public class PhoneAuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private MenuMapper menuMapper;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        String refreshJwt = JwtUtil.createJWT(userId, REFRESH_JWT_TTL);
        //把用户信息存入redis
        redisCache.setCacheObject( ACCESS_TOKEN + userId, loginUser);
        redisCache.setCacheObject( REFRESH_TOKEN + userId, loginUser);
        //把token和userInfo封装，返回
        //把User转换成UserInfoVo
        UserInfoVo userInfoVo = BeanCopyUtils.copeBean(loginUser.getUser(), UserInfoVo.class);
        userInfoVo.setTypeName(roleMapper.selectById(ROLE_MAP.get(loginUser.getUser().getType())).getName());
//        List<MenuVo> auths =  roleMapper.getMenuList(ROLE_MAP.get(loginUser.getUser().getType()));
//        List<MenuVo> auths = menuMapper.selectMenuByUserId(loginUser.getUser().getId());
//        UserLoginVo vo = new UserLoginVo(jwt, userInfoVo, auths);
        UserLoginVo vo = new UserLoginVo(jwt, refreshJwt, userInfoVo);
        PrintWriter out = response.getWriter();
        out.write(JSONObject.toJSONString(ResponseResult.okResult(vo)));
        out.flush();
        out.close();
    }
}
