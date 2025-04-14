package exceptions;

public class WrongArgumentException extends Exception {
    public WrongArgumentException(String message) {
        super("Ошибка с аргументом команды: " + message);
    }
}
