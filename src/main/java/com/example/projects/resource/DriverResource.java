package com.example.projects.resource;

import com.example.projects.model.Driver;
import com.example.projects.model.DriverStatus;
import com.example.projects.model.request.CreateDriverRequest;
import com.example.projects.service.CabService;
import com.example.projects.storage.StoredDriver;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.swagger.annotations.Api;
import io.swagger.jaxrs.PATCH;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Singleton
@Produces(MediaType.APPLICATION_JSON)
@Path("/cab/driver")
@Api("Driver Related Resources")
public class DriverResource {

    private final CabService cabService;

    @Inject
    public DriverResource(CabService cabService) {
        this.cabService = cabService;
    }

    @POST
    @Path("/")
    public Driver addDriver(@NotNull @Valid CreateDriverRequest request) throws Exception {
        return cabService.addDriver(request);
    }

    @GET
    @Path("/{id}")
    public Driver getDriver(@PathParam("id") String id,
                                  @QueryParam("allowInactive")@DefaultValue("false") boolean allowInactive,
                                  @QueryParam("status") DriverStatus status) throws Exception {
        return cabService.getDriver(id, allowInactive, status);
    }

    @PATCH
    @Path("/{id}/{lat}/{lng}")
    public boolean updateDriverLocation(@PathParam("id") String id,
                                        @QueryParam("lat") Double lat,
                                        @QueryParam("lng") Double lng) throws Exception {
        return cabService.updateDriverLocation(id, lat, lng);
    }

    @PATCH
    @Path("/{id}/{status}")
    public boolean updateDriverStatus(@PathParam("id") String id,
                                      @QueryParam("status") DriverStatus status) throws Exception {
        return cabService.updateDriverStatus(id, status);
    }
}
