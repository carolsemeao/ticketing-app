package ch.zli.m223.controller.ticketing;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.zli.m223.controller.ticketing.dto.BookingDto;
import ch.zli.m223.controller.ticketing.dto.BookingUpdateDto;
import ch.zli.m223.service.ticketing.TicketingService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/member/bookings")
@RequiredArgsConstructor
public class TicketingMemberController {

    private final TicketingService ticketingService;

    @GetMapping("")
    public List<BookingDto> getBookings(Principal principal) {
        return ticketingService.getBookingsListForUser(principal.getName()).stream()
        .map((booking) -> new BookingDto(booking)).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    void deleteBooking(@PathVariable("id") Long id) {
        ticketingService.deleteBooking(id);
    }

    @PutMapping("/{id}")
    public BookingDto updateBooking(
        @PathVariable("id") Long id,
        @RequestBody BookingUpdateDto booking
    ){
        return new BookingDto(ticketingService.updateBooking(id, booking.status, booking.roomName, booking.isFullDay));
    }

    @PostMapping("")
    BookingDto createBooking(Principal principal, @RequestBody BookingDto booking) {
        return new BookingDto(ticketingService.addBooking(booking.roomName, booking.date, booking.isFullDay, principal.getName()));
    }
}
