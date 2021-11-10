package cqrs.queries.readStore;

import java.util.Date;
import java.util.LinkedList;

public class ReadStore {

    LinkedList<RoomEntity> rooms = new LinkedList<>();
    LinkedList<BookingEntity> bookings = new LinkedList<>();

    public ReadStore (){
        rooms.add(new RoomEntity(1,2));
        rooms.add(new RoomEntity(2,2));
        rooms.add(new RoomEntity(3,2));
        rooms.add(new RoomEntity(4,4));
        rooms.add(new RoomEntity(5,4));
        rooms.add(new RoomEntity(6,4));
        rooms.add(new RoomEntity(7,5));
        rooms.add(new RoomEntity(8,5));
        rooms.add(new RoomEntity(9,5));
        rooms.add(new RoomEntity(10,6));
        rooms.add(new RoomEntity(11,6));
    }

    public LinkedList<BookingEntity> getBookingsBetween (Date from, Date to){

        LinkedList<BookingEntity> bookingEntities = new LinkedList<>();
        bookings.forEach(bookingEntity -> {
            if(from.before(bookingEntity.getFrom()) && to.after(bookingEntity.getTo())){

                bookingEntities.add(bookingEntity);
            }
        });
        return bookingEntities;
    }

    public LinkedList<RoomEntity> getFreeRooms (Date from, Date to, int numOfPersons){
        LinkedList<RoomEntity> freeRooms = new LinkedList<>();
        rooms.forEach(roomEntity -> {
            if(roomEntity.getPersons() >= numOfPersons){
                if(roomEntity.isFreeBetween(from, to)){
                    freeRooms.add(roomEntity);
                }

            }
        });
        return freeRooms;
    }


    public void addBookingEntity(BookingEntity bookingEntity){

        for(int i = 0; i < rooms.size(); i++)
        {
            if(rooms.get(i).getRoomNr() == bookingEntity.getRoomNr()) {
                rooms.get(i).addBooking(bookingEntity);
                bookings.add(bookingEntity);
            }

        }
    }

    public void cancelBooking(String bookingNr){
        for(int i = 0; i < bookings.size(); i++)
        {
            if(bookings.get(i).getBookingNr().equals(bookingNr)) {
                bookings.get(i).cancel();
            }

        }

    }

}
