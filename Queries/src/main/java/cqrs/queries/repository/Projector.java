package cqrs.queries.repository;

public class Projector {

    private ReadStore readStore;

    public Projector(ReadStore readStore){
        this.readStore = readStore;
    }


    public void projectBookingCreatedEvent(String reservationNr, int roomNr, Date dateFrom, Date dateTo, int numOfPersons, String fname, String lname ){

    }

    public void projectBookingCancelled(String reservationNr){

        BookingEntity bookingEntity = readStore.findBookingByReservationNr(reservationNr);


    }
}
