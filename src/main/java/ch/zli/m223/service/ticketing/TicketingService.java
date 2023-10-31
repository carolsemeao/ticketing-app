package ch.zli.m223.service.ticketing;

import java.util.List;

import ch.zli.m223.model.AppUser;
import ch.zli.m223.model.Booking;
import ch.zli.m223.service.ticketing.exception.BookingAlreadyExistsException;
import ch.zli.m223.service.ticketing.exception.BookingNotFoundException;

public interface TicketingService {
    
    /**
     * @return a possibly empty list of bookings
     */
    List<Booking> getBookingsList();
    

    /**
     * 
     * @param roomName room name
     * @param date date of booking
     * @param isFullDay whether booking is full day or not
     * @param user user
     * @return the newly created booking
     * @throws BookingAlreadyExistsException, InvalidBookingException
     */
    Booking addBooking(String roomName, String date,
        Boolean isFullDay, AppUser user);


    /**
     * 
     * @param id the booking id
     * @return the booking
     * @throws BookingNotFoundException
     */
    Booking getBooking(Long id);


    /**
     * @see TicketingService#deleteBooking(Long id)
     * 
     * @param id the booking id
     */
    void deleteBooking(Long id);

    /**
     * 
     * @param id the booking id
     * @param status the booking status
     * @param isFullDay whether booking is a full day or not
     * @return the changed booking
     * @throws InvalidIdException, BookingAlreadyExistsException, BookingNotFoundException
     */
    Booking updateBooking(
        Long id, String status, String roomName, Boolean isFullDay
    );


    /**
     * 
     * @param id the booking id (required)
     * @param status the new status
     * @return the changed booking
     * @throws InvalidIdException, BookingNotFoundException
     */
    Booking setStatus(Long id, String status);

    List<Booking> getBookingsListForUser(String name);
}
