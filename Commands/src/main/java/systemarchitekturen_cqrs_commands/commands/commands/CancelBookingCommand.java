package systemarchitekturen_cqrs_commands.commands.commands;

public class CancelBookingCommand {

    private String bookingNr;

    public CancelBookingCommand (String bookingNr){
        this.bookingNr = bookingNr;
    }

    public String getBookingNr() {
        return bookingNr;
    }
}
