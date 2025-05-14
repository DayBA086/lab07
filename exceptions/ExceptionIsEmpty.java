package exceptions;

public class ExceptionIsEmpty extends Exception {
    public ExceptionIsEmpty(String message) {
        super(message);
    }

    public ExceptionIsEmpty() {
        super("La estructura de datos está vacía.");
    }
}
