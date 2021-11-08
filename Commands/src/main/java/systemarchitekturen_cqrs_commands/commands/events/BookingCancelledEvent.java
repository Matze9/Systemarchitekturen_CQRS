package systemarchitekturen_cqrs_commands.commands.events;

public class BookingCancelledEvent extends Event {
    private String bookingNr;


    public BookingCancelledEvent (String bookingNr, int roomNumber){
        super("Booking cancelled", roomNumber);
        this.bookingNr = bookingNr;
    }

    @Override
    public String toString (){
        return "BookingCreatedEvent{" +
                "eventId='" + this.getId() + '\'' +
                "description='" + this.getDescription() + '\'' +
                "bookingNr='" + bookingNr + '\'' +
                ", roomNr=" + this.getRoomNr();
    }

    public String getBookingNr() {
        return bookingNr;
    }
}
