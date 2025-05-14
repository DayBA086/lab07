package exceptions;

public class ItemNoFound extends Exception {
    public ItemNoFound(String message) {
        super(message);
    }

    public ItemNoFound() {
        super("El elemento no se encuentra en la estructura de datos.");
    }
}
