package cqrs.queries.services;

import cqrs.queries.DTOs.BookingDTO;
import cqrs.queries.DTOs.RoomDTO;
import cqrs.queries.repository.ReadRepositoryImpl;

import java.util.Date;
import java.util.LinkedList;

public class ReadServicesImpl implements ReadServices{
    ReadRepositoryImpl repository;

    public ReadServicesImpl (ReadRepositoryImpl repository){
        this.repository = repository;
    }

    @Override
    public LinkedList<BookingDTO> getBookingsBetween(Date from, Date to) {
        return null;
    }

    @Override
    public LinkedList<RoomDTO> getRooms(Date from, Date to, int numberOfPersons) {
        return null;
    }
}
