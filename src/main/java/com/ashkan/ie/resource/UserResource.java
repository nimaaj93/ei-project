package com.ashkan.ie.resource;

import com.ashkan.ie.model.JWTToken;
import com.ashkan.ie.model.input.AuthenticateModel;
import com.ashkan.ie.model.input.ResetPassModel;
import com.ashkan.ie.model.input.UserRegistrationModel;
import com.ashkan.ie.model.input.UserUpdateModel;
import com.ashkan.ie.security.jwt.JWTFilter;
import com.ashkan.ie.security.jwt.TokenProvider;
import com.ashkan.ie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by K550 VX on 6/6/2019.
 */
@Component
@Path("/api/v1/user")
public class UserResource {

    @Autowired
    private UserService userService;
    @Autowired
    private TokenProvider tokenProvider;
    @Autowired
    private AuthenticationManager authenticationManager;

    @POST
    @Path("/authenticate")
    public Response authenticate(AuthenticateModel model) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(model.getUsername(), model.getPassword());

        Authentication authentication = this.authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.createToken(authentication, model.isRememberMe());
        JWTToken jwtToken = new JWTToken();
        jwtToken.setIdToken(jwt);
        return Response.ok().entity(jwtToken).type(MediaType.APPLICATION_JSON).build();
    }

    @POST
    public Response register(UserRegistrationModel model) {
        userService.register(model);
        return Response.ok("OK").build();
    }

    @GET
    @Path("/profile")
    public Response getProfile() {
        return Response.ok(userService.getProfile()).type(MediaType.APPLICATION_JSON).build();
    }

    @PUT
    @Path("/password-update")
    public Response updatePassword(ResetPassModel model) {
        userService.resetPassword(model);
        return Response.ok("OK").type(MediaType.APPLICATION_JSON).build();
    }

    @PUT
    public Response updateProfile(UserUpdateModel model) {
        userService.updateUser(model);
        return Response.ok("OK").type(MediaType.APPLICATION_JSON).build();
    }

    @GET
    public Response getAllUsers() {
        return Response.ok(userService.getAllUsers())
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

}
