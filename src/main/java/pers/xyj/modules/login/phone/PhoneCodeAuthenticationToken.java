package pers.xyj.modules.login.phone;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * 身份令牌
 */
public class PhoneCodeAuthenticationToken extends AbstractAuthenticationToken {

    private final Object phone;

    private Object p_code;

    private Object type;

    public PhoneCodeAuthenticationToken(Object phone, Object p_code, Object type, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.phone = phone;
        this.p_code = p_code;
        this.type = type;
        super.setAuthenticated(true);
    }

    public PhoneCodeAuthenticationToken(String phone, String p_code, String type) {
        super(null);
        this.phone = phone;
        this.p_code = p_code;
        this.type = type;
        setAuthenticated(false);
    }

    @Override
    public Object getCredentials() {
        return p_code;
    }

    @Override
    public Object getPrincipal() {
        return phone;
    }

    public Object getType() {
        return type;
    }
}
