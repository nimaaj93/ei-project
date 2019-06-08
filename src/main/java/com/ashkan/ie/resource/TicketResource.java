package com.ashkan.ie.resource;

import com.ashkan.ie.model.input.TicketCreateModel;
import com.ashkan.ie.model.input.TicketStatusUpdateModel;
import com.ashkan.ie.service.TicketingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by K550 VX on 6/7/2019.
 */
@Component
@Path("/api/v1/ticket")
public class TicketResource {

    @Autowired
    private TicketingService ticketingService;

    @POST
    public Response createTicket(TicketCreateModel model) {
        return Response.ok(ticketingService.createTicket(model))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    @PATCH
    @Path("/status")
    public Response updateStatus(TicketStatusUpdateModel model) {
        return Response.ok(ticketingService.updateStatus(model))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

}
