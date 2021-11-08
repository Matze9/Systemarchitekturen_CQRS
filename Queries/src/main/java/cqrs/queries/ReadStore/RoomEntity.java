package cqrs.queries.ReadStore;

import java.util.Date;

public class RoomEntity {
    private int roomNr;
    private int persons;
    private Date freeFrom;
    private Date freeTo;


    public RoomEntity(int roomNr, int persons, Date freeFrom, Date freeTo){
        this.roomNr = roomNr;
        this.persons = persons;
        this.freeFrom = freeFrom;
        this.freeTo = freeTo;
    }

    public int getRoomNr() {
        return roomNr;
    }

    public int getPersons() {
        return persons;
    }

    public Date getFreeFrom() {
        return freeFrom;
    }

    public Date getFreeTo() {
        return freeTo;
    }


}
