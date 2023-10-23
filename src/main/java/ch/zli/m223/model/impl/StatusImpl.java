package ch.zli.m223.model.impl;

import ch.zli.m223.model.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity(name = "Status")
public class StatusImpl implements Status {

    @Id
    @GeneratedValue
    Long id;

    @Column(nullable = false)
    String statusName;
    
    public StatusImpl(String status) {
        this.statusName = status;
    }
    
    // JPA purposes only
    protected StatusImpl() {}

    @Override
    public String getStatusName() {
        return statusName;
    }

    @Override
    public Long getId() {
        return id;
    }
}
