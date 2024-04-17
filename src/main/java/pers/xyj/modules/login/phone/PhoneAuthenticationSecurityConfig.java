package pers.xyj.modules.login.phone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import pers.xyj.modules.login.phone.handler.PhoneAuthFailureHandler;
import pers.xyj.modules.login.phone.handler.PhoneAuthSuccessHandler;


@Component
public class PhoneAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private PhoneAuthSuccessHandler phoneAuthSuccessHandler;
    @Autowired
    private PhoneAuthFailureHandler phoneAuthFailureHandler;
    @Autowired
    private PhoneCodeAuthenticationProvider phoneCodeAuthenticationProvider;

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        PhoneCodeAuthenticationFilter emailCodeAuthenticationFilter = new PhoneCodeAuthenticationFilter();
        emailCodeAuthenticationFilter.setAuthenticationManager(builder.getSharedObject(AuthenticationManager.class));
        emailCodeAuthenticationFilter.setAuthenticationSuccessHandler(phoneAuthSuccessHandler);
        emailCodeAuthenticationFilter.setAuthenticationFailureHandler(phoneAuthFailureHandler);

        builder.authenticationProvider(phoneCodeAuthenticationProvider);
        builder.addFilterAfter(emailCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
