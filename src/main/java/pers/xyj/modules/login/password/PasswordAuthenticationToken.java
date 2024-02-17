package pers.xyj.modules.login.password;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class PasswordAuthenticationToken extends AbstractAuthenticationToken {

    private final Object account;

    private Object password;

    private Object type;

    public PasswordAuthenticationToken(Object account, Object password, Object type, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.account = account;
        this.password = password;
        this.type = type;
        super.setAuthenticated(true);
    }

    public PasswordAuthenticationToken(String account, String password, String type) {
        super(null);
        this.account = account;
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
        return account;
    }

    public Object getType() {
        return type;
    }

}
