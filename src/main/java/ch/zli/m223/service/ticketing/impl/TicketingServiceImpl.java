package ch.zli.m223.service.ticketing.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import ch.zli.m223.model.AppUser;
import ch.zli.m223.model.Booking;
import ch.zli.m223.repository.StatusRepository;
import ch.zli.m223.repository.TicketingRepository;
import ch.zli.m223.service.ticketing.TicketingService;
import ch.zli.m223.service.ticketing.exception.BookingNotFoundException;
import ch.zli.m223.service.ticketing.exception.InvalidBookingException;
import ch.zli.m223.service.ticketing.exception.InvalidIdException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TicketingServiceImpl implements TicketingService {

    private final TicketingRepository ticketingRepository;
    private final StatusRepository statusRepository;


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
    public Booking updateBooking(Long id, String status, String date, Boolean isFullDay, AppUser user) {
        // TODO
        //var booking = getBooking(id);
        
        /*return ticketingRepository.update(
            booking, getStatus(status), date, isFullDay, user != null ? booking.getUser() : user
        );*/
        return null;
    }


    @Override
    public Booking setStatus(Long id, String statusName) {
        var booking = getBooking(id);
        
        var status = statusRepository.findByStatusName(statusName).orElseThrow(() -> {
            throw new InvalidBookingException();
        });
        
        return ticketingRepository.update(
            booking, 
            status, null, null
        );
    }

    @Override
    public Booking addBooking(String roomName, String status, String date, Boolean isFullDay, AppUser user) {
        
        if (roomName == null || roomName.isBlank() ||
        date == null || date.isBlank()) {
            throw new InvalidBookingException();
        }
        var res = statusRepository.findByStatusName(status).orElseThrow(() -> {
            throw new InvalidBookingException();
        });

        return ticketingRepository.insertBooking(roomName, res, date, isFullDay, user);
    }

    @Override
    public List<Booking> getBookingsListForUser(String email) {
        try {
            return ticketingRepository.findByUsername(email);
        } catch (Exception e) {
            throw new BookingNotFoundException();
        }
    }
    
}
