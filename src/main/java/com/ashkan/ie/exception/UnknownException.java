package com.ashkan.ie.exception;

import javax.ws.rs.core.Response;

/**
 * Created by K550 VX on 6/6/2019.
 */
public class UnknownException extends BaseException {

    public UnknownException() {
        super("unknown.exception", Response.Status.INTERNAL_SERVER_ERROR);
    }
}
