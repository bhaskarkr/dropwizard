package com.example.projects.resource;

import com.example.projects.model.Base;
import com.example.projects.model.request.BaseCreateRequest;
import com.example.projects.service.BaseService;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Singleton
@Produces(MediaType.APPLICATION_JSON)
@Path("/base")
@Api("My Base Resources")
public class BaseResource {

    private final BaseService baseService;

    @Inject
    public BaseResource(BaseService baseService){
        this.baseService = baseService;
    }

    @GET
    @Path("/{id}")
    @ApiOperation("Test for GET Req")
    public Base getBase(@PathParam("id") String id,
                        @QueryParam("allowInactive")@DefaultValue("false") boolean allowInactive) throws Exception{
        return baseService.getBase(id, allowInactive);
    }

    @POST
    @Path("/")
    @ApiOperation("Test for GET Req")
    public Base postBase(BaseCreateRequest request) throws Exception{
        return baseService.createBase(request);
    }
}
