package systemarchitekturen_cqrs_commands.commands.events;

import java.util.Date;
import java.util.UUID;

public abstract class Event {

    private final UUID id = UUID.randomUUID();
    private final Date created = new Date();
    private String description;
    private int roomNr;
    private String bookingNr;

    public Event(String description, int roomNr, String bookingNr) {
        this.description = description;
        this.roomNr = roomNr;
        this.bookingNr = bookingNr;
    }

    public UUID getId() {
        return id;
    }

    public Date getCreated() {
        return created;
    }

    public String getDescription() {
        return description;
    }

    public int getRoomNr() {
        return roomNr;
    }

    public String getBookingNr() {
        return bookingNr;
    }
}
