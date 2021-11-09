package systemarchitekturen_cqrs_commands.commands.commandHandlers;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import systemarchitekturen_cqrs_commands.commands.commands.CancelBookingCommand;
import systemarchitekturen_cqrs_commands.commands.commands.CreateBookingCommand;
import systemarchitekturen_cqrs_commands.commands.events.BookingCancelledEvent;
import systemarchitekturen_cqrs_commands.commands.events.BookingCreatedEvent;
import systemarchitekturen_cqrs_commands.commands.repositories.EventRepository;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.UUID;

public class BookCommandHandler {

    EventRepository eventRepository;
    private final WebClient webClient = WebClient.create("http://localhost:8081");

    public BookCommandHandler(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public void handleCreateBookingCommand (CreateBookingCommand command){

        //Validate Command (Check if Booking in Timerange is possible)
        if(eventRepository.checkIfBookingIsPossible(command.getFrom(), command.getTo(), command.getRoomNr())) {
            UUID bookingNr = UUID.randomUUID();
            this.handleCreateBookingEvent(new BookingCreatedEvent("New booking created", bookingNr.toString(), command.getRoomNr(), command.getFrom(), command.getTo(), command.getFirstName(), command.getLastName(), command.getPhoneNr()));
        } else {
            System.out.println("Booking in Timerange not possible!");
        }
    }

    public void handleCancelBookingCommand (CancelBookingCommand cancelBookingCommand){

        int roomNr = eventRepository.getRoomNrOfBooking(cancelBookingCommand.getBookingNr());

        if(roomNr != -1){
            if(!eventRepository.checkIfBookingIsCancelled(cancelBookingCommand.getBookingNr())) {
                this.handleBookingCancelledEvent(new BookingCancelledEvent(cancelBookingCommand.getBookingNr(), roomNr));
            } else {
                System.out.println("Booking is already cancelled!");
            }
        } else {
            System.out.println("Room does not exist!");
        }
    }

    public void handleCreateBookingEvent(BookingCreatedEvent bookingCreatedEvent) {

        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String from = dateFormat.format(bookingCreatedEvent.getFrom());
        String to = dateFormat.format(bookingCreatedEvent.getTo());

        webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/handleBookingCreatedEvent")
                        .queryParam("reservationNr", bookingCreatedEvent.getId().toString())
                        .queryParam("from", from.toString())
                        .queryParam("to", to.toString())

                        .queryParam("roomNr", bookingCreatedEvent.getRoomNr())
                        .queryParam("firstName", bookingCreatedEvent.getFirstName())
                        .queryParam("lastName", bookingCreatedEvent.getLastName())
                        .build())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)

                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

        System.out.println("CommandsApp Eventhandler!");


        eventRepository.addEvent(bookingCreatedEvent);
    }

    public void handleBookingCancelledEvent (BookingCancelledEvent bookingCancelledEvent){

        webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/handleBookingCancelledEvent")
                        .queryParam("reservationNr", bookingCancelledEvent.getBookingNr())
                        .build())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)

                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

        eventRepository.addEvent(bookingCancelledEvent);

    }
}
