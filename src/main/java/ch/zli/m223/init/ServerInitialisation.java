package ch.zli.m223.init;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import ch.zli.m223.model.impl.StatusImpl;
import ch.zli.m223.repository.RoleRepository;
import ch.zli.m223.repository.StatusRepository;
import ch.zli.m223.roles.UserRoles;
import ch.zli.m223.service.ticketing.TicketingService;
import ch.zli.m223.service.user.UserService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ServerInitialisation implements ApplicationRunner {

    private final UserService userService;
    private final RoleRepository roleRepository;
    private final TicketingService ticketingService;
    private final StatusRepository statusRepository;

    @Value("${test.data.create.user:false}")
    private boolean createTestDataForUser;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (createTestDataForUser) {
            roleRepository.addRole(UserRoles.member);
            roleRepository.addRole(UserRoles.admin);
            
            userService.addUser("admin", "admin", "admin@admin.com", "admin");
            userService.addUser("Max", "Werner", "max@werner.com", "maxwerner");
            userService.addUser("Mini", "Max", "mini@max.com", "minimax");
            userService.addUser("Lady", "Gaga", "lady@gaga.com", "ladygaga");
            userService.addUser("Carolina", "Semeao", "caro@sem.ch", "carosem");
            userService.addUser("Jesper", "Fröden", "jes@froden.sw", "jesfroden");

            statusRepository.save(new StatusImpl("Pending"));
            statusRepository.save(new StatusImpl("Accepted"));
            statusRepository.save(new StatusImpl("Declined"));

            ticketingService.addBooking("Room 1", "23.10.2023", false, "max@werner.com");
            ticketingService.addBooking("Room 2", "25.10.2023", false, "max@werner.com");
            ticketingService.addBooking("Room 1", "28.10.2023", true, "max@werner.com");
            ticketingService.addBooking("Room 2", "30.10.2023", true, "max@werner.com");
            ticketingService.addBooking("Room 2", "31.10.2023", true, "jes@froden.sw");
        }
    }
    
}
