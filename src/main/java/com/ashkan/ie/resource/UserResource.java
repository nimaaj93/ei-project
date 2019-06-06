package com.ashkan.ie.resource;

import com.ashkan.ie.model.JWTToken;
import com.ashkan.ie.model.input.AuthenticateModel;
import com.ashkan.ie.model.input.UserRegistrationModel;
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

import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
//    @Produces(MediaType.APPLICATION_JSON)
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
    @Path("/register")
    public Response register(UserRegistrationModel model) {
        userService.register(model);
        return Response.ok("OK").build();
    }

}
