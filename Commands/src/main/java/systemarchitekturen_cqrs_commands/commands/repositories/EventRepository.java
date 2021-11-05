package systemarchitekturen_cqrs_commands.commands.repositories;

import systemarchitekturen_cqrs_commands.commands.events.BookingCreatedEvent;
import systemarchitekturen_cqrs_commands.commands.events.Event;

import java.util.LinkedList;

public class EventRepository {

    LinkedList<Event> events = new LinkedList<>();


    public void addEvent (Event event) {
        events.add(event);
    }

    public LinkedList<String> getRoomEvents (int roomNr){
        LinkedList<String> roomEvents = new LinkedList<>();
        events.forEach((event -> {
            if(event instanceof BookingCreatedEvent){
                if ( ((BookingCreatedEvent) event).getRoomNr() == roomNr){
                    roomEvents.add(event.toString());
                }
            }
        }));

        return roomEvents;
    }

}
