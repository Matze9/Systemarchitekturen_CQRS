package systemarchitekturen_cqrs_commands.commands.commandHandlers.exceptions;

public class TimeRangeException extends Exception{

    public TimeRangeException (){
        super("Booking in given Timerange not possible!");
    }
}
