package com.example.projects.core;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class BaseExceptionMapper implements ExceptionMapper<BaseException> {
    @Override
    public Response toResponse(BaseException e) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(GenericError.builder()
                        .code(e.getErrorCode().name())
                        .message(e.getMessage())
                        .build())
                .build();
    }
}
