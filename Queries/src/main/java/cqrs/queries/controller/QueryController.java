package cqrs.queries.controller;

import cqrs.queries.DTOs.BookingDTO;
import cqrs.queries.DTOs.RoomDTO;

import cqrs.queries.readStore.ReadStore;
import cqrs.queries.repository_v2.Projector;
import cqrs.queries.repository_v2.ReadRepository;
import cqrs.queries.repository_v2.ReadRepositoryImpl;
import cqrs.queries.services.ReadServices;
import cqrs.queries.services.ReadServicesImpl;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.LinkedList;

@RestController
public class QueryController {

    ReadStore readStore = new ReadStore();
    ReadRepository repository = new ReadRepositoryImpl(readStore);
    ReadServices readServices = new ReadServicesImpl(repository);
     Projector projector = new Projector(repository);

    @GetMapping("/api/getFreeRooms")
    @ResponseBody
    public RoomDTO[] getFreeRooms(@RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy") Date from,
                                  @RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy") Date to,
                                  @RequestParam int numberOfPersons) {


        LinkedList<RoomDTO> rooms = readServices.getFreeRooms(from, to, numberOfPersons);
        RoomDTO[] roomDTOS = rooms.toArray(new RoomDTO[rooms.size()]);

        return roomDTOS;
    }

    @GetMapping("/api/getBookingsBetween")
    @ResponseBody
    public BookingDTO[] getBookings(@RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy") Date from,
                                    @RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy") Date to) {

        LinkedList<BookingDTO> bookings = readServices.getBookingsBetween(from, to);
        BookingDTO[] bookingDTOs = bookings.toArray(new BookingDTO[bookings.size()]);

        return bookingDTOs;
    }


    ///////////////Servicebus Controller//////////////



    @PostMapping("/api/handleBookingCreatedEvent")
    @ResponseBody
    public void handleBookingCreatedEvent(@RequestParam (value = "reservationNr") String reservationNr,
                                          @RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy") Date from,
                                          @RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy") Date to,
                                          @RequestParam (value = "roomNr") int roomNr,
                                          @RequestParam (value = "firstName") String firstName,
                                          @RequestParam (value = "lastName") String lastName) {

        projector.projectBookingCreatedEvent(reservationNr, roomNr, from, to, 5, firstName, lastName);

    }

    @PostMapping("/api/handleBookingCancelledEvent")
    @ResponseBody
    public void handleBookingCancelledEvent(@RequestParam (value = "reservationNr") String reservationNr) {

        projector.projectBookingCancelled(reservationNr);

    }


}
