package cqrs.queries.controller;

import cqrs.queries.DTOs.BookingDTO;
import cqrs.queries.DTOs.RoomDTO;
import cqrs.queries.ReadStore.ReadStore;
import cqrs.queries.repository.ReadRepositoryImpl;
import cqrs.queries.services.ReadServices;
import cqrs.queries.services.ReadServicesImpl;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.LinkedList;

@RestController
public class QueryController {

    ReadStore readStore = new ReadStore();
    ReadRepositoryImpl repository = new ReadRepositoryImpl(readStore);
    ReadServices readServices = new ReadServicesImpl(repository);
    // Projector projector = new Projector(readStore);

    @GetMapping("/api/getFreeRooms")
    @ResponseBody
    public RoomDTO[] getFreeRooms(@RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy") Date from,
                                  @RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy") Date to,
                                  @RequestParam int numberOfPersons) {

        RoomDTO roomDTO = new RoomDTO(5, 2);
        LinkedList<RoomDTO> rooms = readServices.getRooms(from, to, numberOfPersons);
        rooms.add(roomDTO);



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

        System.out.println("ReservationNr: " + reservationNr +
                " From: " + from +
                " To: " + to +

                " roomNr: " + roomNr +
                " firstName: " + firstName +
                " lastName: " + lastName);

        // projector.projectBookingCreatedEvent(...)

    }

    @PostMapping("/api/handleBookingCancelledEvent")
    @ResponseBody
    public void handleBookingCancelledEvent(@RequestParam (value = "reservationNr") String reservationNr) {

        System.out.println("Cancel Booking: " + reservationNr);

        // projector.projectBookingCancelledEvent(...)

    }


}
