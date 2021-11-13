package cqrs.queries.repository_v2;

import cqrs.queries.DTOs.BookingDTO;
import cqrs.queries.DTOs.RoomDTO;
import cqrs.queries.readStore.BookingEntity;

import java.util.Date;
import java.util.LinkedList;

public interface ReadRepository {

    public void addBooking(BookingEntity bookingEntity );
    public void cancelBooking (String bookingNr);
    public LinkedList<BookingDTO> getBookingsBetween (Date from, Date to);
    public LinkedList<RoomDTO> getFreeRooms (Date from, Date to, int numOfPersons);

    }
