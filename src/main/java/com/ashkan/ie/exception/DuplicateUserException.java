package com.ashkan.ie.exception;


import javax.ws.rs.core.Response;

/**
 * Created by K550 VX on 6/6/2019.
 */
public class DuplicateUserException extends BaseException {

    public DuplicateUserException() {
        super("duplicate.user", Response.Status.BAD_REQUEST);
    }

}
