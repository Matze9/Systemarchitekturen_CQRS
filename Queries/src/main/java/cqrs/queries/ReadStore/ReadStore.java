package cqrs.queries.ReadStore;

import java.util.LinkedList;

public class ReadStore {

    LinkedList<BookingEntity> bookings = new LinkedList<>();
    LinkedList<RoomEntity> rooms = new LinkedList<>();


    public void addBookingEntity(BookingEntity bookingEntity){
        bookings.add(bookingEntity);
    }

    public void addRoomEntity(RoomEntity roomEntity){
        rooms.add(roomEntity);
    }


    public BookingEntity findBookingByReservationNr(String reservationNr){
        for (BookingEntity b : bookings){
            if(b.getReservationNr().equals(reservationNr)){
                return b;
            }
        }
        return null;
    }

}
