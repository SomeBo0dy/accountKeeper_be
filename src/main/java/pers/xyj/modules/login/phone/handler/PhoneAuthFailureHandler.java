package pers.xyj.modules.login.phone.handler;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import pers.xyj.modules.accountKeeper.domain.ResponseResult;
import pers.xyj.modules.common.enums.AppHttpCodeEnum;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
@Component
public class PhoneAuthFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.PHONE_LOGIN_FAIL, exception.getMessage());
        out.write(JSONObject.toJSONString(result));
        out.flush();
        out.close();
    }
}
