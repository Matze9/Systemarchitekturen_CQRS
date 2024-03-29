package cqrs.queries.readStore;

import java.util.Date;
import java.util.LinkedList;

public class RoomEntity {
    private int roomNr;
    private int persons;
    private LinkedList<BookingEntity> bookings = new LinkedList<>();


    public RoomEntity(int roomNr, int persons){
        this.roomNr = roomNr;
        this.persons = persons;

    }

    public int getRoomNr() {
        return roomNr;
    }

    public int getPersons() {
        return persons;
    }

    public void addBooking (BookingEntity bookingEntity) {
        bookings.add(bookingEntity);
    }

    public LinkedList<BookingEntity> getBookings() {
        return bookings;
    }

    public boolean isFreeBetween (Date from, Date to){
        for(int i = 0; i < bookings.size(); i++){

            if (((from.after(bookings.get(i).getFrom()) && from.before(bookings.get(i).getTo()))
            || (from.compareTo(bookings.get(i).getFrom()) == 0)
            || (to.after(bookings.get(i).getFrom()) && to.before(bookings.get(i).getTo()))) && !bookings.get(i).getState().equals("cancelled")) {
                return false;
            }

        }
        return true;
    }
}
