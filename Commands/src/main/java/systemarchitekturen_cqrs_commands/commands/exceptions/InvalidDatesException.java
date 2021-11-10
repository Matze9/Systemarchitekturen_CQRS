package systemarchitekturen_cqrs_commands.commands.exceptions;

public class InvalidDatesException extends Exception{

    public InvalidDatesException (){
        super("Invalid Dates, please check!");
    }
}
