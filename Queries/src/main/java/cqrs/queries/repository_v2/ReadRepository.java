package cqrs.queries.repository_v2;



import cqrs.queries.DTOs.BookingDTO;
import cqrs.queries.DTOs.RoomDTO;
import cqrs.queries.readStore.BookingEntity;
import cqrs.queries.readStore.ReadStore;
import cqrs.queries.readStore.RoomEntity;

import java.util.Date;
import java.util.LinkedList;

public class ReadRepository {
    private ReadStore readStore;


    public ReadRepository(ReadStore readStore){
        this.readStore = readStore;
    }

    public void addBooking(BookingEntity bookingEntity ){
        readStore.addBookingEntity(bookingEntity);
    }

    public void cancelBooking (String bookingNr){
        readStore.cancelBooking(bookingNr);
    }

    public LinkedList<BookingDTO> getBookingsBetween (Date from, Date to){
        LinkedList<BookingEntity> entities = readStore.getBookingsBetween(from, to);
        LinkedList<BookingDTO> dtos = new LinkedList<>();
        entities.forEach(entity -> {
            dtos.add(new BookingDTO(entity.getBookingNr(), entity.getRoomNr(), entity.getFrom(), entity.getTo(), entity.getFirstName(), entity.getLastName(), entity.getState()));
        });
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
