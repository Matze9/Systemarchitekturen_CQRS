package systemarchitekturen_cqrs_commands.commands.controller;


import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import systemarchitekturen_cqrs_commands.commands.commandHandlers.BookCommandHandler;
import systemarchitekturen_cqrs_commands.commands.exceptions.AlreadyCancelledException;
import systemarchitekturen_cqrs_commands.commands.exceptions.InvalidDatesException;
import systemarchitekturen_cqrs_commands.commands.exceptions.TimeRangeException;
import systemarchitekturen_cqrs_commands.commands.commands.CancelBookingCommand;
import systemarchitekturen_cqrs_commands.commands.commands.CreateBookingCommand;
import systemarchitekturen_cqrs_commands.commands.repositories.EventRepository;

import java.util.Date;
import java.util.LinkedList;

@RestController
public class CommandsController {

    EventRepository eventRepository = new EventRepository();
    BookCommandHandler bookingCommandHandler = new BookCommandHandler(eventRepository);


    @PostMapping("/api/createBooking")
    @ResponseBody
    public String create(@RequestParam int roomNr,
                         @RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy") Date from,
                         @RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy") Date to,
                         @RequestParam String firstName,
                         @RequestParam String lastName,
                         @RequestParam String phoneNr) {

        try {
            String bookingNr = bookingCommandHandler.handleCreateBookingCommand(new CreateBookingCommand(roomNr, from, to, firstName, lastName, phoneNr));
            return bookingNr;
        } catch (TimeRangeException | InvalidDatesException e) {
            e.printStackTrace();
            return e.getMessage();
        }

    }

    @PostMapping("/api/cancelBooking")
    @ResponseBody
    public String cancelBooking(@RequestParam String bookingNr) {

        try {
            bookingCommandHandler.handleCancelBookingCommand(new CancelBookingCommand(bookingNr));
            return "Booking cancelled";
        } catch (AlreadyCancelledException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @GetMapping("/api/getEvents")
    @ResponseBody
    public String[] getEvents(@RequestParam int roomNr) {

        LinkedList<String> eventList = eventRepository.getRoomEvents(roomNr);

        String[] eventArr = eventList.toArray(new String[eventList.size()]);

        return eventArr;
    }

}
