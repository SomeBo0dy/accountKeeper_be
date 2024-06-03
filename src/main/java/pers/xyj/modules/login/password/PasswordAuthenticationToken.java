package pers.xyj.modules.login.password;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class PasswordAuthenticationToken extends AbstractAuthenticationToken {

    private final Object phone;

    private Object password;

    private Object type;

    public PasswordAuthenticationToken(Object phone, Object password, Object type, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.phone = phone;
        this.password = password;
        this.type = type;
        super.setAuthenticated(true);
    }

    public PasswordAuthenticationToken(String phone, String password, String type) {
        super(null);
        this.phone = phone;
        this.password = password;
        this.type = type;
        setAuthenticated(false);
    }

    @Override
    public Object getCredentials() {
        return password;
    }

    @Override
    public Object getPrincipal() {
        return phone;
    }

    public Object getType() {
        return type;
    }

}
