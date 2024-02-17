package pers.xyj.modules.login.password;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class    PasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final String ACCOUNT = "account";

    private final String PASSWORD = "password";

    private final String TYPE = "type";

    @Autowired
    @Override
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    public PasswordAuthenticationFilter() {
        super(new AntPathRequestMatcher("/login/password", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if (!"POST".equals(request.getMethod())) {
            throw new AuthenticationServiceException("请求方式有误: " + request.getMethod());
        }
        //如果请求的参数格式不是json，直接异常
        if (!request.getContentType().contains(MediaType.APPLICATION_JSON_VALUE)) {
            throw new AuthenticationServiceException("参数不是json：" + request.getMethod());
        }
        String account = "";
        String password = "";
        String type = "";
        try {
            Map<String, String> map = JSONObject.parseObject(request.getInputStream(), Map.class);
            account = map.get(ACCOUNT);
            password = map.get(PASSWORD);
            type = map.get(TYPE);
        } catch (IOException e) {
            throw new AuthenticationServiceException("参数不正确：" + request.getMethod());
        }
        if (account == null) {
            throw new BadCredentialsException("账号参数缺失");
        }
        if (password == null) {
            throw new BadCredentialsException("密码参数缺失");
        }
        if (type == null) {
            throw new BadCredentialsException("角色类型参数缺失");
        }
        account = account.trim();
        password = password.trim();
        PasswordAuthenticationToken authenticationToken = new PasswordAuthenticationToken(account, password, type);
        setDetails(request, authenticationToken);
        //交给 AuthenticationManager 进行认证
        return this.getAuthenticationManager().authenticate(authenticationToken);

    }

    public void setDetails(HttpServletRequest request, PasswordAuthenticationToken token) {
        token.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }
}
