package ch.zli.m223.controller.ticketing.dto;

import ch.zli.m223.model.Booking;
import ch.zli.m223.model.Status;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BookingDto {
    public Long id;
    public Long userId;
    public String roomName;
    public String date;
    public Status status;
    public Boolean isFullDay;

    public BookingDto(Booking booking) {
        userId = booking.getUser().getId();
        id = booking.getId();
        roomName = booking.getRoomName();
        date = booking.getBookingDate();
        status = booking.getStatus();
        isFullDay = booking.getIsFullDay();
    }
}
