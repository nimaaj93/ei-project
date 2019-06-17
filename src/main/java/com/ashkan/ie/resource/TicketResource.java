package com.ashkan.ie.resource;

import com.ashkan.ie.model.input.TicketCommentCreateModel;
import com.ashkan.ie.model.input.TicketCreateModel;
import com.ashkan.ie.model.input.TicketStatusUpdateModel;
import com.ashkan.ie.service.TicketingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
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

    @POST
    @Path("/comment")
    public Response addComment(TicketCommentCreateModel model) {
        return Response.ok(ticketingService.addComment(model))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    @GET
    @Path("/{id}")
    public Response getTicket(@PathParam("id") Long id) {
        return Response.ok(ticketingService.getTicket(id))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    @GET
    @Path("/user-created")
    public Response getUserCreatedTickets(@QueryParam("page") Integer page,
                                          @QueryParam("size") Integer size) {
        return Response.ok(ticketingService.getUserCreatedTickets(PageRequest.of(page, size)))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    @GET
    @Path("/user-assigned")
    public Response getUserAssignedTickets(@QueryParam("page") Integer page,
                                           @QueryParam("size") Integer size) {
        return Response.ok(ticketingService.getUserAssignedTickets(PageRequest.of(page, size)))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    @GET
    public Response pageAllTickets(@QueryParam("page") Integer page,
                                   @QueryParam("size") Integer size) {

        return Response.ok(ticketingService.getAll(PageRequest.of(page, size)))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

}
