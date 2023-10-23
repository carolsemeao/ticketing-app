package ch.zli.m223.model.impl;

import ch.zli.m223.model.Booking;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity(name="Booking")
public class BookingImpl implements Booking {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String roomName;

    @ManyToOne
    private AppUserImpl appUserId;

    private boolean isFullDay;
    private String status;
    private String date;

    public BookingImpl(
        String roomName, String status, String date,
        boolean isFullDay, AppUserImpl userId
    ) {
        this.roomName = roomName;
        this.status = status;
        this.date = date;
        this.isFullDay = isFullDay;
        this.appUserId = userId;
    }

    /** For JPA use only */
    protected BookingImpl(){}

    @Override
    public Long getId() {
        return id;
    }
    @Override
    public String getRoomName() {
        return roomName;
    }
    @Override
    public Long getUserId() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUserId'");
    }
    @Override
    public boolean getIsFullDay() {
        return isFullDay;
    }
    @Override
    public String getStatus() {
        return status;
    }
    @Override
    public String getBookingDate() {
        return date;
    }
}
