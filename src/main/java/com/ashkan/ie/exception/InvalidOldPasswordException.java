package com.ashkan.ie.exception;

import javax.ws.rs.core.Response;

/**
 * Created by K550 VX on 6/7/2019.
 */
public class InvalidOldPasswordException extends BaseException {

    public InvalidOldPasswordException() {
        super("invalid.old.password", Response.Status.BAD_REQUEST);
    }
}
