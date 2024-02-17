package pers.xyj.modules.login.password;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import pers.xyj.modules.login.password.handler.PasswordAuthenticationFailureHandler;
import pers.xyj.modules.login.password.handler.PasswordAuthenticationSuccessHandler;



@Component
public class PasswordAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    PasswordAuthenticationSuccessHandler successHandler;

    @Autowired
    PasswordAuthenticationFailureHandler failureHandler;

    @Autowired
    PasswordAuthenticationProvider authenticationProvider;

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        PasswordAuthenticationFilter filter = new PasswordAuthenticationFilter();
        filter.setAuthenticationManager(builder.getSharedObject(AuthenticationManager.class));
        filter.setAuthenticationSuccessHandler(successHandler);
        filter.setAuthenticationFailureHandler(failureHandler);

        builder.authenticationProvider(authenticationProvider);
        builder.addFilterAfter(filter, UsernamePasswordAuthenticationFilter.class);
    }
}
