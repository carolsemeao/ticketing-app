package ch.zli.m223.service.ticketing.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import ch.zli.m223.model.AppUser;
import ch.zli.m223.model.Booking;
import ch.zli.m223.model.Status;
import ch.zli.m223.repository.StatusRepository;
import ch.zli.m223.repository.TicketingRepository;
import ch.zli.m223.service.ticketing.TicketingService;
import ch.zli.m223.service.ticketing.exception.BookingNotFoundException;
import ch.zli.m223.service.ticketing.exception.InvalidBookingException;
import ch.zli.m223.service.ticketing.exception.InvalidIdException;
import ch.zli.m223.service.ticketing.exception.StatusNotFoundException;
import ch.zli.m223.service.user.UserService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TicketingServiceImpl implements TicketingService {

    private final TicketingRepository ticketingRepository;
    private final StatusRepository statusRepository;
    private final UserService userService;


    @Override
    public List<Booking> getBookingsList() {
        return new ArrayList<>(ticketingRepository.findAll());
    }

    @Override
    public Booking getBooking(Long id) {
        if (id == null) {
            throw new InvalidIdException();
        }
        return ticketingRepository.findById(id).orElseThrow(
            () -> new BookingNotFoundException()
        );
    }

    @Override
    public void deleteBooking(Long id) {
        if (id == null) {
            throw new InvalidIdException();
        }
        ticketingRepository.deleteById(id);
    }

    @Override
    public Booking updateBooking(Long id, String statusName, String roomName, Boolean isFullDay) {
        var booking = getBooking(id);
        
        return ticketingRepository.update(
            booking, getStatus(statusName), roomName, isFullDay
        );
    }


    @Override
    public Booking setStatus(Long id, String statusName) {
        var booking = getBooking(id);
        
        return ticketingRepository.update(
            booking, 
            getStatus(statusName), null, null 
        );
    }

    @Override
    public Booking addBooking(String roomName, String date, Boolean isFullDay, String username) {
        AppUser user = userService.getUserByName(username);
        String statusName = Status.pending;
        Status status = getStatus(statusName);

        if (roomName == null || roomName.isBlank() ||
        date == null || date.isBlank()) {
            throw new InvalidBookingException();
        }

        return ticketingRepository.addBooking(roomName, status, date, isFullDay, user);
    }


    @Override
    public List<Booking> getBookingsListForUser(String email) {
        try {
            return ticketingRepository.findByUsername(email);
        } catch (Exception e) {
            throw new BookingNotFoundException();
        }
    }
    
     private Status getStatus(String statusName) {
        if (statusName == null) {
            throw new InvalidBookingException();
        }
        return statusRepository.findStatusByStatus(statusName.trim())
            .orElseThrow(StatusNotFoundException::new);
    }
}
