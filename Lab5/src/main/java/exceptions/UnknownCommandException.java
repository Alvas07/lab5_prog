package exceptions;

public class UnknownCommandException extends Exception {
    public UnknownCommandException(String message) {
        super("Неизвестное имя команды: " + message);
    }
}
