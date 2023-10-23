package ch.zli.m223.controller.ticketing;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.zli.m223.controller.ticketing.dto.BookingDto;
import ch.zli.m223.service.ticketing.TicketingService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/admin/bookings")
@RequiredArgsConstructor
public class TicketingAdminController {
    private final TicketingService ticketingService;
    
    @GetMapping("")
    List<BookingDto> getAllUsers() {
        return ticketingService.getBookingsList().stream()
            .map((booking) -> new BookingDto(booking))
            .collect(Collectors.toList());
    }
}
