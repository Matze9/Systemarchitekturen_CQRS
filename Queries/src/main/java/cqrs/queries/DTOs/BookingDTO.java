package cqrs.queries.DTOs;

import java.util.Date;

public class BookingDTO {

    private String bookingNr;
    private int roomNr;
    private Date from;
    private Date to;
    private String firstName;
    private String lastName;

    public BookingDTO(String bookingNr, int roomNr, Date from, Date to, String firstName, String lastName) {
        this.bookingNr = bookingNr;
        this.roomNr = roomNr;
        this.from = from;
        this.to = to;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getBookingNr() {
        return bookingNr;
    }

    public int getRoomNr() {
        return roomNr;
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
}
