package com.ashkan.ie.security.jwt;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created by K550 VX on 6/19/2019.
 */
public class MyCustomAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private Long userId;

    public MyCustomAuthenticationToken(Object principal, Object credentials, Long userId) {
        super(principal, credentials);
        this.userId = userId;
    }

    public MyCustomAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities, Long userId) {
        super(principal, credentials, authorities);
        this.userId = userId;
    }
}
