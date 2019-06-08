package com.ashkan.ie.exception;

import javax.ws.rs.core.Response;

/**
 * Created by K550 VX on 6/7/2019.
 */
public class TicketNotFoundException extends BaseException {

    public TicketNotFoundException() {
        super("ticket.not.found", Response.Status.NOT_FOUND);
    }
}
