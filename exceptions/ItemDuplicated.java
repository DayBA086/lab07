package exceptions;
public class ItemDuplicated extends Exception {
    public ItemDuplicated(String message) {
        super(message);
    }
    public ItemDuplicated() {
        super("el elemento ya existe ");
    }
}
