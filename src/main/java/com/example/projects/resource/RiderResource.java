package com.example.projects.resource;

import com.example.projects.model.DriverStatus;
import com.example.projects.model.request.CreateDriverRequest;
import com.example.projects.model.request.CreateRiderRequest;
import com.example.projects.service.CabService;
import com.example.projects.storage.StoredDriver;
import com.example.projects.storage.StoredRider;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.swagger.annotations.Api;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Singleton
@Produces(MediaType.APPLICATION_JSON)
@Path("/cab/rider")
@Api("Rider Related Resources")
public class RiderResource {

    private final CabService cabService;

    @Inject
    public RiderResource(CabService cabService) {
        this.cabService = cabService;
    }

    @POST
    @Path("/")
    public StoredRider addRider(@NotNull @Valid CreateRiderRequest request) throws Exception {
        return cabService.addRider(request);
    }

    @GET
    @Path("/{id}")
    public StoredRider getRider(@PathParam("id") String id,
                                @QueryParam("allowInactive")@DefaultValue("false") boolean allowInactive) throws Exception {
        return cabService.getRider(id, allowInactive);
    }

}
