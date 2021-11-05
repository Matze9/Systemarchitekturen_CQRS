package systemarchitekturen_cqrs_commands.commands.events;

import java.util.Date;

public class BookingCreatedEvent extends Event{

    private String bookingNr;
    private int roomNr;
    private Date from;
    private Date to;
    private String firstName;
    private String lastName;
    private String phoneNr;

    public BookingCreatedEvent(String description, String bookingNr, int roomNr, Date from, Date to, String firstName, String lastName, String phoneNr) {
        super(description);
        this.bookingNr = bookingNr;
        this.roomNr = roomNr;
        this.from = from;
        this.to = to;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNr = phoneNr;
    }

    public int getRoomNr() {
        return roomNr;
    }

    @Override
    public String toString() {

        return "BookingCreatedEvent{" +
                "eventId='" + this.getId() + '\'' +
                "description='" + this.getDescription() + '\'' +
                "bookingNr='" + bookingNr + '\'' +
                ", roomNr=" + roomNr +
                ", from=" + from +
                ", to=" + to +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNr='" + phoneNr + '\'' +
                '}';
    }
}
