package systemarchitekturen_cqrs_commands.commands.commands;

import java.util.Date;

public class CreateBookingCommand {

    private int roomNr;
    private Date from;
    private Date to;
    private String firstName;
    private String lastName;
    private String phoneNr;

    public CreateBookingCommand(int roomNr, Date from, Date to, String firstName, String lastName, String phoneNr) {
        this.roomNr = roomNr;
        this.from = from;
        this.to = to;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNr = phoneNr;
    }

    public int getRoomNr() {
        return roomNr;
    }

    public Date getFrom() {
        return from;
    }

    public Date getTo() {
        return to;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNr() {
        return phoneNr;
    }
}
