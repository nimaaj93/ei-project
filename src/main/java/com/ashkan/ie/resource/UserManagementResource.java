package com.ashkan.ie.resource;

import com.ashkan.ie.service.UserManagementService;
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
@Path("/api/v1/user-management")
public class UserManagementResource {

    @Autowired
    private UserManagementService userManagementService;

    @GET
    @Path("/list")
    public Response pageUsers(@QueryParam("page") Integer page,
                              @QueryParam("size") Integer size) {

        return Response.ok(userManagementService.listUsers(PageRequest.of(page, size)))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    @PATCH
    @Path("/{id}/enable")
    public Response enableUser(@PathParam("id") Long id) {
        return Response.ok(userManagementService.enableUser(id))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    @PATCH
    @Path("/{id}/disable")
    public Response disableUser(@PathParam("id") Long id) {
        return Response.ok(userManagementService.disableUser(id))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") Long id) {
        userManagementService.deleteUser(id);
        return Response.ok("OK")
                .build();
    }

    @PATCH
    @Path("/{id}/activate")
    public Response activateUser(@PathParam("id") Long id) {
        return Response.ok(userManagementService.activateUser(id))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    @GET
    @Path("/unactivated")
    public Response unactivatedUsers(@QueryParam("page") Integer page,
                                     @QueryParam("size") Integer size) {
        return Response.ok(
                userManagementService.unactivatedList(PageRequest.of(page, size)))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

}