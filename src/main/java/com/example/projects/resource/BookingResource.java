package com.example.projects.resource;

import com.example.projects.model.Booking;
import com.example.projects.model.Driver;
import com.example.projects.model.request.CreateBookingRequest;
import com.example.projects.service.CabService;
import com.example.projects.storage.StoredDriver;
import com.example.projects.storage.StoredRides;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Singleton
@Produces(MediaType.APPLICATION_JSON)
@Path("/cab/booking")
@Api("Booking Related")
public class BookingResource {

    private final CabService cabService;

    @Inject
    public BookingResource(CabService cabService) {
        this.cabService = cabService;
    }

    @Path("/")
    @POST
    @ApiOperation("Add booking")
    public Booking addBooking(CreateBookingRequest request) throws Exception{
        return cabService.addBooking(request);
    }

    @Path("/cabs")
    @POST
    @ApiOperation("Get nearest cabs")
    public List<Driver> getNearestCabs(@NotNull @QueryParam("lat") Double lat,
                                       @NotNull @QueryParam("lng") Double lng) throws Exception{
        return cabService.getNearest(lat, lng);
    }

    @Path("/driver/{driverId}/list")
    @GET
    @ApiOperation("Get Bookings for Driver")
    public List<Booking> getBookingsForDriver(@NotNull @QueryParam("driverId") String driverId) throws Exception{
        return cabService.getBookingsForDriver(driverId);
    }

    @Path("/rider/{riderId}/list")
    @GET
    @ApiOperation("Get Bookings for Rider")
    public List<Booking> getBookingsForRider(@NotNull @QueryParam("driverId") String driverId) throws Exception{
        return cabService.getBookingsForRider(driverId);
    }

}
