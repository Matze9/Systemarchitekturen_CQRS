package cqrs.queries.services;

import cqrs.queries.DTOs.BookingDTO;
import cqrs.queries.DTOs.RoomDTO;
import cqrs.queries.repository_v2.ReadRepository;
import cqrs.queries.repository_v2.ReadRepositoryImpl;

import java.util.Date;
import java.util.LinkedList;

public class ReadServicesImpl implements ReadServices{
    ReadRepository repository;

    public ReadServicesImpl (ReadRepository repository){
        this.repository = repository;
    }

    @Override
    public LinkedList<BookingDTO> getBookingsBetween(Date from, Date to) {
        return repository.getBookingsBetween(from, to);
    }

    @Override
    public LinkedList<RoomDTO> getFreeRooms(Date from, Date to, int numberOfPersons) {
        return repository.getFreeRooms(from, to, numberOfPersons);
    }
}
