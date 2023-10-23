package ch.zli.m223.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.zli.m223.model.impl.BookingImpl;

public interface TicketingRepository extends JpaRepository<BookingImpl, Long> {
    
}
