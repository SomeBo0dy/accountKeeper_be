package pers.xyj.modules.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import pers.xyj.modules.common.filter.JwtAuthenticationTokenFilter;
import pers.xyj.modules.login.password.PasswordAuthenticationSecurityConfig;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Lazy
    @Autowired
    private PasswordAuthenticationSecurityConfig passwordAuthenticationSecurityConfig;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                // swagger start
                .antMatchers("/swagger-ui.html")
                .antMatchers( "/swagger-resources/**")
                .antMatchers("/v2/api-docs")
                .antMatchers("configuration/ui")
                .antMatchers("configuration/security")
                .antMatchers("/webjars/**");
                // swagger end

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //关闭csrf
                .csrf().disable()
                //使用自定义的账号密码过滤器
                .apply(passwordAuthenticationSecurityConfig).and()
                //不通过Session获取SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()

                // 对于登录接口 允许匿名访问
                .antMatchers("/login").anonymous()
                .antMatchers("/users/register").anonymous()
                .antMatchers("/websocket").anonymous()
//                .antMatchers("/goods/**").anonymous()
                .antMatchers("/logout").authenticated()
                .antMatchers("/chatUserLink").authenticated()
                .antMatchers("/chatMessage").authenticated()
                .antMatchers("/user/**").authenticated()
                .antMatchers("/users/userinfo").authenticated()
                .antMatchers("/upload/**").authenticated()
                .antMatchers("/users/userInfo").authenticated()
                .antMatchers("/sys/**").access("hasAuthority('system:admin')")




                // 除上面外的所有请求全部不需要认证即可访问
                .anyRequest().permitAll();

        http.logout().disable();

        //配置异常处理器
        http.exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);
        //添加到过滤器链
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        //允许跨域
        http.cors();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Bean
    public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        //此处可添加别的规则,目前只设置 允许双 //
        firewall.setAllowUrlEncodedDoubleSlash(true);
        return firewall;
    }
}
