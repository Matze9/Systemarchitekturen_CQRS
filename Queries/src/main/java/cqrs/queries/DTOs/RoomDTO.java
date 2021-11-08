package cqrs.queries.DTOs;

public class RoomDTO {
    private int roomNr;
    private int numOfPersons;

    public RoomDTO (int roomNr, int numOfPersons){
        this.roomNr = roomNr;
        this.numOfPersons = numOfPersons;
    }

    public int getRoomNr() {
        return roomNr;
    }

    public void setRoomNr(int roomNr) {
        this.roomNr = roomNr;
    }

    public int getNumOfPersons() {
        return numOfPersons;
    }

    public void setNumOfPersons(int numOfPersons) {
        this.numOfPersons = numOfPersons;
    }
}
