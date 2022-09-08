package ir.mahdi.universityservice.exceptions;

public class ItemAlreadyExist extends RuntimeException {
    public ItemAlreadyExist(String itemName) {
        super(String.format("%s already exist", itemName));
    }
}
