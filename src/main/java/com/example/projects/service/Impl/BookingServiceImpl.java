package com.example.projects.service.Impl;

import com.example.projects.core.BaseException;
import com.example.projects.core.ErrorCode;
import com.example.projects.enums.VehicleType;
import com.example.projects.model.BillingConfig;
import com.example.projects.model.Booking;
import com.example.projects.model.request.BookingRequest;
import com.example.projects.repository.BookingRepository;
import com.example.projects.service.BookingService;
import com.example.projects.storage.StoredBooking;
import com.example.projects.util.ParkingUtils;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static com.example.projects.constants.BaseConstants.HOURS_PER_DAY;

@Singleton
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final BillingConfig billingConfig;

    @Inject
    public BookingServiceImpl(BookingRepository bookingRepository,
                              BillingConfig billingConfig) {
        this.bookingRepository = bookingRepository;
        this.billingConfig = billingConfig;
    }


    @Override
    public Booking registerBooking(BookingRequest bookingRequest) throws Exception {
        return ParkingUtils.toDto(bookingRepository.save(ParkingUtils.toDao(bookingRequest)));
    }

    @Override
    public Booking getById(String bookingId, boolean allowInactive) throws Exception {
        Optional<StoredBooking> storedBooking = bookingRepository.getById(bookingId, false);
        if(!storedBooking.isPresent())
            throw BaseException.error(ErrorCode.INVALID_REQUEST, "Booking Id not found");
        return ParkingUtils.toDto(storedBooking.get());
    }

    @Override
    public int finishBooking(String bookingId) throws Exception {
        Optional<StoredBooking> storedBooking = bookingRepository.getById(bookingId, false);
        if(!storedBooking.isPresent())
            throw BaseException.error(ErrorCode.INVALID_REQUEST, "Booking Id not found");
        int cost = 0;
        int hours = 5 + calculateDurationInHours(storedBooking.get().getCreatedAt(), storedBooking.get().getUpdatedAt());
        int i = 0;
        for(; hours > 0 && i < billingConfig.getHourSplit().size(); i++) {
            int costRate = billingConfig.getRateCard().get(storedBooking.get().getType()).get(i);
            cost += hours < billingConfig.getHourSplit().get(i)
                    ? hours * costRate
                    : billingConfig.getHourSplit().get(i) * costRate;
            hours -= billingConfig.getHourSplit().get(i);
        }
        return cost;
    }

    private int calculateDurationInHours(Date entry, Date exit) {
        long diff = exit.getTime() - entry.getTime();
        return (int)((TimeUnit.MILLISECONDS.toMinutes(diff)/HOURS_PER_DAY) + (TimeUnit.MILLISECONDS.toMinutes(diff) % 24 > 0 ? 1 : 0 ));
    }
}
