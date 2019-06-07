package com.ashkan.ie.resource.exceptionmapper;

import com.ashkan.ie.exception.BaseException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by K550 VX on 6/7/2019.
 */
@Provider
@Component
public class MyExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception e) {
        if (e instanceof BaseException) {
            BaseException exOcurred = (BaseException) e;
            ExceptionResponse entity = new ExceptionResponse();
            entity.setKey(exOcurred.getMessage());
            entity.setException(exOcurred.getClass().getName());
            return Response.status(exOcurred.getStatus())
                    .entity(entity)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }

        if (e instanceof BadCredentialsException) {
            ExceptionResponse entity = new ExceptionResponse();
            entity.setKey("bad.credentials");
            entity.setException(e.getClass().getName());
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(entity)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }

        ExceptionResponse entity = new ExceptionResponse();
        entity.setKey("unknown.exception");
        entity.setException(e.getClass().getName());

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(entity)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
