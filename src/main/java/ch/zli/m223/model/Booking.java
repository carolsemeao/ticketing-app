package ch.zli.m223.model;

public interface Booking {
    Long getId();
    String getRoomName();
    Long getUserId();
    boolean getIsFullDay();
    String getStatus();
    String getBookingDate();
}
