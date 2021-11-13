package cqrs.queries.repository_v2;



import cqrs.queries.readStore.BookingEntity;

import java.util.Date;

public class Projector {



    private ReadRepository readRepository;

    public Projector(ReadRepository readRepository){
        this.readRepository = readRepository;
    }


    public void projectBookingCreatedEvent(String reservationNr, int roomNr, Date from, Date to, int numOfPersons, String fname, String lname ){
        readRepository.addBooking(new BookingEntity(reservationNr, roomNr, from, to, fname, lname));
    }

    public void projectBookingCancelled(String reservationNr){
        readRepository.cancelBooking(reservationNr);

    }
}
