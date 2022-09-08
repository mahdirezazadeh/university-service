package ir.mahdi.universityservice.exceptions;

public class ItemDoesNotExistException extends RuntimeException {
    public ItemDoesNotExistException(String itemName) {
        super(String.format("%s does not exist", itemName));
    }
}
