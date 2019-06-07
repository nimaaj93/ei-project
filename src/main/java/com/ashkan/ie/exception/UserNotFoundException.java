package com.ashkan.ie.exception;

import javax.ws.rs.core.Response;

/**
 * Created by K550 VX on 6/6/2019.
 */
public class UserNotFoundException extends BaseException {

    public UserNotFoundException() {
        super("user.not.found", Response.Status.NOT_FOUND);
    }
}
