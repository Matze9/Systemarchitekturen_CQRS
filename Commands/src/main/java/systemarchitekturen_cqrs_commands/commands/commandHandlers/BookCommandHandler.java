package systemarchitekturen_cqrs_commands.commands.commandHandlers;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import systemarchitekturen_cqrs_commands.commands.exceptions.AlreadyCancelledException;
import systemarchitekturen_cqrs_commands.commands.exceptions.InvalidDatesException;
import systemarchitekturen_cqrs_commands.commands.exceptions.TimeRangeException;
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

    public String handleCreateBookingCommand (CreateBookingCommand command) throws TimeRangeException, InvalidDatesException {

        if (command.getFrom().after(command.getTo())) throw new InvalidDatesException();

        //Validate Command (Check if Booking in Timerange is possible)
        if(eventRepository.checkIfBookingIsPossible(command.getFrom(), command.getTo(), command.getRoomNr())) {
            String bookingNr = UUID.randomUUID().toString();

            this.handleCreateBookingEvent(new BookingCreatedEvent("New booking created", bookingNr, command.getRoomNr(), command.getFrom(), command.getTo(), command.getFirstName(), command.getLastName(), command.getPhoneNr()));
            return bookingNr;
        } else {
            throw new TimeRangeException();
        }
    }

    public void handleCancelBookingCommand (CancelBookingCommand cancelBookingCommand) throws AlreadyCancelledException {

        int roomNr = eventRepository.getRoomNrOfBooking(cancelBookingCommand.getBookingNr());

        if(roomNr != -1){
            if(!eventRepository.checkIfBookingIsCancelled(cancelBookingCommand.getBookingNr())) {
                this.handleBookingCancelledEvent(new BookingCancelledEvent(cancelBookingCommand.getBookingNr(), roomNr));
            } else {
                throw new AlreadyCancelledException();
            }
        }
    }

    public void handleCreateBookingEvent(BookingCreatedEvent bookingCreatedEvent) {

        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String from = dateFormat.format(bookingCreatedEvent.getFrom());
        String to = dateFormat.format(bookingCreatedEvent.getTo());

        webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/handleBookingCreatedEvent")
                        .queryParam("reservationNr", bookingCreatedEvent.getBookingNr())
                        .queryParam("from", from)
                        .queryParam("to", to)
                        .queryParam("roomNr", bookingCreatedEvent.getRoomNr())
                        .queryParam("firstName", bookingCreatedEvent.getFirstName())
                        .queryParam("lastName", bookingCreatedEvent.getLastName())
                        .build())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)

                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

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
