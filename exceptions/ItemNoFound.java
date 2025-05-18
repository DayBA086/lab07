package exceptions;

public class ItemNoFound extends Exception {
    public ItemNoFound(String message) {
        super(message);
    }

    public ItemNoFound() {
        super("el elemento no esta");
    }
}
