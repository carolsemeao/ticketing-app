package ch.zli.m223.model;

public interface Status {
    public final String pending = "Pending";
    public final String accepted = "Accepted";
    public final String declined = "Declined";

    Long getId();
    String getStatusName();
}
