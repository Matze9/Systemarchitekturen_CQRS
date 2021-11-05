package systemarchitekturen_cqrs_commands.commands.controller;


import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import systemarchitekturen_cqrs_commands.commands.commandHandlers.BookingCommandHandler;
import systemarchitekturen_cqrs_commands.commands.commands.CreateBookingCommand;
import systemarchitekturen_cqrs_commands.commands.repositories.EventRepository;

import java.util.Date;
import java.util.LinkedList;

@RestController
public class CommandsController {

    EventRepository eventRepository = new EventRepository();
    BookingCommandHandler bookingCommandHandler = new BookingCommandHandler(eventRepository);


    @PostMapping("/api/createBooking")
    @ResponseBody
    public String create(@RequestParam int roomNr,
                         @RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy") Date from,
                         @RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy") Date to,
                         @RequestParam String firstName,
                         @RequestParam String lastName,
                         @RequestParam String phoneNr ) {

        bookingCommandHandler.handleCreateBookingCommand(new CreateBookingCommand(roomNr, from, to, firstName, lastName, phoneNr));

        return "From: " + from + " To: " + to;
    }

    @GetMapping("/api/getEvents")
    @ResponseBody
    public String[] getEvents(@RequestParam int roomNr ) {

        LinkedList<String> eventList = eventRepository.getRoomEvents(roomNr);

        String[] eventArr = eventList.toArray(new String[eventList.size()]);

        return eventArr;
    }

}
