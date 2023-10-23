package ch.zli.m223.service.status.impl;

import org.springframework.stereotype.Service;

import ch.zli.m223.model.Status;
import ch.zli.m223.model.impl.StatusImpl;
import ch.zli.m223.repository.StatusRepository;
import ch.zli.m223.service.status.StatusService;
import ch.zli.m223.service.status.exception.InvalidIdException;
import ch.zli.m223.service.status.exception.InvalidStatusException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StatusServiceImpl implements StatusService {
    private final StatusRepository statusRepository;

    @Override
    public Status addStatus(String statusName) {
        if (statusName == null || statusName.isBlank()) {
            throw new InvalidStatusException();
        }
        return statusRepository.save(new StatusImpl(statusName));
    }

    @Override
    public void deleteStatus(Long id) {
        if (id == null) {
            throw new InvalidIdException();
        }
        statusRepository.deleteById(id);
    }
    
}
