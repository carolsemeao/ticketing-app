package ch.zli.m223.service.status;

import ch.zli.m223.model.Status;

public interface StatusService {
    Status addStatus(String statusName);
    void deleteStatus(Long id);
}
