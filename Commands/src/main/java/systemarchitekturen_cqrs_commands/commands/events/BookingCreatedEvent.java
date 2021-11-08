package systemarchitekturen_cqrs_commands.commands.events;

import java.util.Date;

public class BookingCreatedEvent extends Event{

    private String bookingNr;
    private Date from;
    private Date to;
    private String firstName;
    private String lastName;
    private String phoneNr;

    public BookingCreatedEvent(String description, String bookingNr, int roomNr, Date from, Date to, String firstName, String lastName, String phoneNr) {
        super(description, roomNr);
        this.bookingNr = bookingNr;
        this.from = from;
        this.to = to;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNr = phoneNr;
    }


    public String getBookingNr() {
        return bookingNr;
    }

    public Date getFrom() {
        return from;
    }

    public Date getTo() {
        return to;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNr() {
        return phoneNr;
    }

    @Override
    public String toString() {

        return "BookingCreatedEvent{" +
                "eventId='" + this.getId() + '\'' +
                "description='" + this.getDescription() + '\'' +
                "bookingNr='" + bookingNr + '\'' +
                ", roomNr=" + this.getRoomNr() +
                ", from=" + from +
                ", to=" + to +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNr='" + phoneNr + '\'' +
                '}';
    }
}
