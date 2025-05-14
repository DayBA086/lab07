package exceptions;
public class ItemDuplicated extends Exception {
    public ItemDuplicated(String message) {
        super(message);
    }
    public ItemDuplicated() {
        super("El elemento ya existe en la estructura de datos.");
    }
}
