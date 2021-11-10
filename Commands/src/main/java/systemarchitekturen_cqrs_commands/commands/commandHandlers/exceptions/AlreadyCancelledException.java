package systemarchitekturen_cqrs_commands.commands.commandHandlers.exceptions;

public class AlreadyCancelledException extends Exception{
    public AlreadyCancelledException (){
        super("The Booking was already cancelled!");
    }
}
