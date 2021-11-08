package cqrs.queries.services;

import cqrs.queries.DTOs.BookingDTO;
import cqrs.queries.DTOs.RoomDTO;

import java.util.Date;
import java.util.LinkedList;

public interface ReadServices {

    LinkedList<BookingDTO> getBookingsBetween(Date from, Date to);

    LinkedList<RoomDTO> getRooms(Date from, Date to, int numberOfPersons);

}
