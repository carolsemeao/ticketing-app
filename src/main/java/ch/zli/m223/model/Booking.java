package ch.zli.m223.model;

public interface Booking {
    Long getId();
    String getRoomName();
    AppUser getUser();
    Boolean getIsFullDay();
    Status getStatus();
    String getBookingDate();

    Long getUserId();
}
