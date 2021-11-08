package cqrs.queries.ReadStore;

import java.util.Date;

public class BookingEntity {

    private String reservationNr;
    private int roomNr;
    private Date from;



    private Date to;


    public BookingEntity (String reservationNr, int roomNr, Date from, Date to){
        this.reservationNr = reservationNr;
        this.roomNr = roomNr;
        this.from = from;
        this.to = to;
    }


    public String getReservationNr() {
        return reservationNr;
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


}
