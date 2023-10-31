package ch.zli.m223.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.zli.m223.model.Status;
import ch.zli.m223.model.impl.StatusImpl;

public interface StatusRepository extends JpaRepository<StatusImpl, Long>{
    Optional<Status> findStatusByStatus(String name);
}
