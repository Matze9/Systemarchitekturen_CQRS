package cqrs.queries.controller.repository_v2;



import cqrs.queries.readStore_v2.BookingEntity;
import cqrs.queries.readStore_v2.ReadStore;

import java.util.Date;

public class Projector {



    private ReadRepositoryImpl readRepository;

    public Projector(ReadRepositoryImpl readRepository){
        this.readRepository = readRepository;
    }


    public void projectBookingCreatedEvent(String reservationNr, int roomNr, Date from, Date to, int numOfPersons, String fname, String lname ){
        System.out.println("I am Projecting!");
        readRepository.addBooking(reservationNr, roomNr, from, to, numOfPersons, fname, lname);
    }

    public void projectBookingCancelled(String reservationNr){
        readRepository.cancelBooking(reservationNr);

    }
}
