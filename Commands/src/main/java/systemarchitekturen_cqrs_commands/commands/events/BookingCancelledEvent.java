package systemarchitekturen_cqrs_commands.commands.events;

public class BookingCancelledEvent extends Event {



    public BookingCancelledEvent (String bookingNr, int roomNumber){
        super("Booking cancelled", roomNumber, bookingNr);

    }

    @Override
    public String toString (){
        return "BookingCancelledEvent{" +
                "eventId='" + this.getId() + '\'' +
                "description='" + this.getDescription() + '\'' +
                "bookingNr='" + this.getBookingNr() + '\'' +
                ", roomNr=" + this.getRoomNr();
    }


}
