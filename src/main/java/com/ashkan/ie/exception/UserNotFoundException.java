package com.ashkan.ie.exception;

/**
 * Created by K550 VX on 6/6/2019.
 */
public class UserNotFoundException extends BaseException {

    public UserNotFoundException() {
        super("user.not.found");
    }
}
