package systemarchitekturen_cqrs_commands.commands.events;

import java.util.Date;
import java.util.UUID;

public abstract class Event {

    private final UUID id = UUID.randomUUID();
    private final Date created = new Date();
    private String description;

    public Event(String description) {
        this.description = description;
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
}
