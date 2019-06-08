package com.ashkan.ie.security;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by K550 VX on 6/8/2019.
 */
public class UserDisabledException extends AuthenticationException {

    public UserDisabledException(String msg, Throwable t) {
        super(msg, t);
    }

    public UserDisabledException(String msg) {
        super(msg);
    }
}
