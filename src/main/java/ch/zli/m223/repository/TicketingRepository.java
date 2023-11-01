package ch.zli.m223.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.zli.m223.model.AppUser;
import ch.zli.m223.model.Booking;
import ch.zli.m223.model.Status;
import ch.zli.m223.model.impl.BookingImpl;
import ch.zli.m223.model.impl.StatusImpl;

public interface TicketingRepository extends JpaRepository<BookingImpl, Long> {
    public default Booking addBooking(String roomName, Status status, String date, Boolean isFullDay, AppUser user) {
        return save(new BookingImpl(user, roomName, (StatusImpl)status, date, isFullDay));
    }

    public default List<Booking> findByStatus(String status) {
        return findAll().stream()
        .filter((booking) -> {
            return booking.getStatus().getStatusName().equals(status);
        }).collect(Collectors.toList());
    };

    public default Booking update (
        Booking booking, Status status, String roomName, Boolean isFullDay
    ) {
        BookingImpl bookingImpl = (BookingImpl) booking;
        if (status!= null) bookingImpl.setStatus((StatusImpl)status);
        if (isFullDay != null) bookingImpl.setIsFullDay(isFullDay);
        if (roomName != null) bookingImpl.setRoomName(roomName);
        return save(bookingImpl);
    }

    public default List<Booking> findByUsername(String name) {
        return findAll().stream()
            .filter(booking -> booking.getUser() != null && name.equals(booking.getUser().getUserName()))
            .collect(Collectors.toList());
    }
}
