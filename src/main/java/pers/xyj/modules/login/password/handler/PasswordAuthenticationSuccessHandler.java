package pers.xyj.modules.login.password.handler;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import pers.xyj.modules.auth.mapper.MenuMapper;
import pers.xyj.modules.common.utils.BeanCopyUtils;
import pers.xyj.modules.common.utils.JwtUtil;
import pers.xyj.modules.common.utils.RedisCache;
import pers.xyj.modules.market.domain.ResponseResult;
import pers.xyj.modules.market.domain.entity.LoginUser;
import pers.xyj.modules.market.domain.vo.UserInfoVo;
import pers.xyj.modules.market.domain.vo.UserLoginVo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;


@Slf4j
@Component
public class PasswordAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Autowired
    private RedisCache redisCache;

    @Autowired
    private MenuMapper menuMapper;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        //把用户信息存入redis
        redisCache.setCacheObject("login:" + userId, loginUser,24, TimeUnit.HOURS);
        //把token和userInfo封装，返回
        //把User转换成UserInfoVo
        UserInfoVo userInfoVo = BeanCopyUtils.copeBean(loginUser.getUser(), UserInfoVo.class);
        UserLoginVo vo = new UserLoginVo(jwt, userInfoVo);
        PrintWriter out = response.getWriter();
        out.write(JSONObject.toJSONString(ResponseResult.okResult(vo)));
        out.flush();
        out.close();
    }
}
