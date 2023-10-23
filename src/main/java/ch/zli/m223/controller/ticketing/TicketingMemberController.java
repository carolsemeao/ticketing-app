package ch.zli.m223.controller.ticketing;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.zli.m223.controller.ticketing.dto.BookingDto;
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
    
}
