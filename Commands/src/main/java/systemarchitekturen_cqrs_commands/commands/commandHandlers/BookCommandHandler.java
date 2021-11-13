package systemarchitekturen_cqrs_commands.commands.commandHandlers;

import systemarchitekturen_cqrs_commands.commands.commands.CancelBookingCommand;
import systemarchitekturen_cqrs_commands.commands.commands.CreateBookingCommand;
import systemarchitekturen_cqrs_commands.commands.events.BookingCancelledEvent;
import systemarchitekturen_cqrs_commands.commands.events.BookingCreatedEvent;
import systemarchitekturen_cqrs_commands.commands.exceptions.AlreadyCancelledException;
import systemarchitekturen_cqrs_commands.commands.exceptions.InvalidDatesException;
import systemarchitekturen_cqrs_commands.commands.exceptions.TimeRangeException;

public interface BookCommandHandler {

    public String handleCreateBookingCommand (CreateBookingCommand command) throws TimeRangeException, InvalidDatesException;
    public void handleCancelBookingCommand (CancelBookingCommand cancelBookingCommand) throws AlreadyCancelledException;

    public void handleCreateBookingEvent(BookingCreatedEvent bookingCreatedEvent);
    public void handleBookingCancelledEvent (BookingCancelledEvent bookingCancelledEvent);
    }
