package systemarchitekturen_cqrs_commands.commands.repositories;

import systemarchitekturen_cqrs_commands.commands.events.BookingCancelledEvent;
import systemarchitekturen_cqrs_commands.commands.events.BookingCreatedEvent;
import systemarchitekturen_cqrs_commands.commands.events.Event;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicReference;

public class EventRepository {

    LinkedList<Event> events = new LinkedList<>();


    public void addEvent(Event event) {
        events.add(event);
    }

    public LinkedList<String> getRoomEvents(int roomNr) {
        LinkedList<String> roomEvents = new LinkedList<>();
        events.forEach((event -> {
            if (event.getRoomNr() == roomNr) {
                roomEvents.add(event.toString());
            }
        }));

        return roomEvents;
    }

    public int getRoomNrOfBooking(String bookingNr) {
        Iterator<Event> iter = events.iterator();
        while (iter.hasNext()) {
            Event event = iter.next();
            if (event instanceof BookingCreatedEvent) {
                if (((BookingCreatedEvent) event).getBookingNr().equals(bookingNr)) {
                    return event.getRoomNr();
                }
            }
        }
        return -1;
    }

    public boolean checkIfBookingIsCancelled(String bookingNr){
        Iterator<Event> iter = events.iterator();
        while (iter.hasNext()) {
            Event event = iter.next();
            if (event instanceof BookingCancelledEvent) {
                if (((BookingCancelledEvent) event).getBookingNr().equals(bookingNr)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkIfBookingIsPossible (Date from, Date to, int roomNr){

        Iterator<Event> iter = events.iterator();
        while (iter.hasNext()) {
            Event event = iter.next();
            if ((from.after(((BookingCreatedEvent) event).getFrom()) && from.before(((BookingCreatedEvent) event).getTo()))
                    || from.compareTo(((BookingCreatedEvent) event).getFrom()) == 0
                    || to.after(((BookingCreatedEvent) event).getFrom()) && to.before(((BookingCreatedEvent) event).getTo())){
                if(event.getRoomNr() == roomNr){
                    return false;
                }
            }
        }
        return true;
    }


}
