package systemarchitekturen_cqrs_commands.commands.commandHandlers;

import systemarchitekturen_cqrs_commands.commands.commands.CreateBookingCommand;
import systemarchitekturen_cqrs_commands.commands.events.BookingCreatedEvent;
import systemarchitekturen_cqrs_commands.commands.repositories.EventRepository;

import java.util.UUID;

public class BookCommandHandler {

    EventRepository eventRepository;

    public BookCommandHandler(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public void handleCreateBookingCommand (CreateBookingCommand command){

        //TODO Validate Command

        UUID bookingNr = UUID.randomUUID();
        this.handleCreateBookingEvent(new BookingCreatedEvent("New booking created", bookingNr.toString(), command.getRoomNr(), command.getFrom(), command.getTo(), command.getFirstName(), command.getLastName(), command.getPhoneNr()  ));

    }

    public void handleCreateBookingEvent(BookingCreatedEvent bookingCreatedEvent) {

        //TODO handle event - publish it to read application

        eventRepository.addEvent(bookingCreatedEvent);
    }
}
