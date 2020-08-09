package com.example.projects.resource;

import com.example.projects.model.Rider;
import com.example.projects.model.request.CreateRiderRequest;
import com.example.projects.service.CabService;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Singleton
@Produces(MediaType.APPLICATION_JSON)
@Path("/cab/rider")
@Api("Rider Related")
public class RiderResource {

    private final CabService cabService;

    @Inject
    public RiderResource(CabService cabService) {
        this.cabService = cabService;
    }

    @POST
    @Path("/")
    @ApiOperation("Add Rider")
    public Rider addRider(@NotNull @Valid CreateRiderRequest request) throws Exception {
        return cabService.addRider(request);
    }

    @GET
    @Path("/{id}")
    @ApiOperation("Get Rider")
    public Rider getRider(@PathParam("id") String id,
                                @QueryParam("allowInactive")@DefaultValue("false") boolean allowInactive) throws Exception {
        return cabService.getRider(id, allowInactive);
    }

}
