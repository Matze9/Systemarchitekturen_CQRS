package cqrs.queries.readStore;

import java.util.Date;

public class BookingEntity {

    private String bookingNr;
    private int roomNr;
    private Date from;
    private Date to;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String state;


    public BookingEntity(String bookingNr, int roomNr, Date from, Date to, String firstName, String lastName) {
        this.bookingNr = bookingNr;
        this.roomNr = roomNr;
        this.from = from;
        this.to = to;
        this.firstName = firstName;
        this.lastName = lastName;
        this.state = "active";
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void cancel (){
        System.out.println("Cancel booking: " + this);
        this.state = "cancelled";
    }

    public String getState() {
        return state;
    }
}
