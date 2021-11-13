package systemarchitekturen_cqrs_commands.commands.repositories;

import systemarchitekturen_cqrs_commands.commands.events.Event;

import java.util.Date;
import java.util.LinkedList;

public interface EventRepository {

    public void addEvent(Event event);
    public LinkedList<String> getRoomEvents(int roomNr);
    public int getRoomNrOfBooking(String bookingNr);
    public boolean checkIfBookingIsCancelled(String bookingNr);
    public boolean checkIfBookingIsPossible (Date from, Date to, int roomNr);


    }
