package ch.zli.m223.model.impl;

import ch.zli.m223.model.AppUser;
import ch.zli.m223.model.Booking;
import ch.zli.m223.model.Status;
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
    private AppUserImpl user;

    @ManyToOne
    private StatusImpl status;

    private Boolean isFullDay;

    private String date;

    public BookingImpl(AppUser user,
        String roomName, StatusImpl status, String date,
        Boolean isFullDay
    ) {
        this.roomName = roomName;
        setStatus(status);
        this.date = date;
        this.isFullDay = isFullDay;
        this.user = (AppUserImpl)user;
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
    public Boolean getIsFullDay() {
        return isFullDay;
    }
    @Override
    public Status getStatus() {
        return status;
    }
    @Override
    public String getBookingDate() {
        return date;
    }

    @Override
    public AppUser getUser() {
        return user;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setIsFullDay(Boolean isFullDay) {
        this.isFullDay = isFullDay;
    }

    public void setStatus(StatusImpl status) {
        this.status = status;
    }

    public Boolean isFullDay()  { return false; }
}
