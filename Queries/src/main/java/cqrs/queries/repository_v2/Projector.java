package cqrs.queries.repository_v2;



import cqrs.queries.readStore.BookingEntity;

import java.util.Date;

public class Projector {



    private ReadRepository readRepositoryImpl;

    public Projector(ReadRepository readRepositoryImpl){
        this.readRepositoryImpl = readRepositoryImpl;
    }


    public void projectBookingCreatedEvent(String reservationNr, int roomNr, Date from, Date to, int numOfPersons, String fname, String lname ){
        readRepositoryImpl.addBooking(new BookingEntity(reservationNr, roomNr, from, to, fname, lname));
    }

    public void projectBookingCancelled(String reservationNr){
        readRepositoryImpl.cancelBooking(reservationNr);

    }
}
