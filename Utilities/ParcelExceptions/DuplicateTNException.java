package Utilities.ParcelExceptions;

public class DuplicateTNException extends ExceptionsValidation{
    public DuplicateTNException() {
        super("Tracking number already exists.");
    }
}
