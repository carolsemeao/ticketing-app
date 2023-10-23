package ch.zli.m223.controller.ticketing.dto;

import ch.zli.m223.model.Booking;
import ch.zli.m223.model.Status;

public class BookingDto {
    public Long id;
    public String roomName;
    public String date;
    public Boolean isFullDay;
    public Status status;
    public Long userId;

    public BookingDto(Booking booking) {
        id = booking.getId();
        roomName = booking.getRoomName();
        date = booking.getBookingDate();
        isFullDay = booking.getIsFullDay();
        status = booking.getStatus();
        userId = booking.getUser().getId();
    }
}
