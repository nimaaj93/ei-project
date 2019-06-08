package com.ashkan.ie.exception;

import javax.ws.rs.core.Response;

/**
 * Created by K550 VX on 6/7/2019.
 */
public class InvalidInputDataException extends BaseException {

    public InvalidInputDataException() {
        super("invalid.input.data", Response.Status.BAD_REQUEST);
    }
}
