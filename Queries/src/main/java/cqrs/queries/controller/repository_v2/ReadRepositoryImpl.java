package cqrs.queries.controller.repository_v2;



import cqrs.queries.DTOs.BookingDTO;
import cqrs.queries.DTOs.RoomDTO;
import cqrs.queries.readStore_v2.BookingEntity;
import cqrs.queries.readStore_v2.ReadStore;
import cqrs.queries.readStore_v2.RoomEntity;

import java.util.Date;
import java.util.LinkedList;

public class ReadRepositoryImpl {
    private ReadStore readStore;


    public ReadRepositoryImpl (ReadStore readStore){
        this.readStore = readStore;
    }

    public void addBooking(String reservationNr, int roomNr, Date from, Date to, int numOfPersons, String fname, String lname ){
        System.out.println("Repository is Adding!");
        readStore.addBookingEntity(new BookingEntity(reservationNr, roomNr, from, to, fname, lname));
    }

    public void cancelBooking (String bookingNr){
        readStore.cancelBooking(bookingNr);
    }

    public LinkedList<BookingDTO> getBookingsBetween (Date from, Date to){
        System.out.println("Repository activated!");
        LinkedList<BookingEntity> entities = readStore.getBookingsBetween(from, to);
        LinkedList<BookingDTO> dtos = new LinkedList<>();
        entities.forEach(entity -> {
            dtos.add(new BookingDTO(entity.getBookingNr(), entity.getRoomNr(), entity.getFrom(), entity.getTo(), entity.getFirstName(), entity.getLastName(), entity.getState()));
        });
        System.out.println("Repository got: " + entities);
        System.out.println("Repository returns: " + dtos);
        return dtos;
    }

    public LinkedList<RoomDTO> getFreeRooms (Date from, Date to, int numOfPersons){

        LinkedList<RoomEntity> roomEntities = readStore.getFreeRooms(from, to, numOfPersons);
        LinkedList<RoomDTO> roomDTOS = new LinkedList<>();

        roomEntities.forEach(roomEntity -> {
            roomDTOS.add(new RoomDTO(roomEntity.getRoomNr(), roomEntity.getPersons()));
        });

        return roomDTOS;
    }

}
