package exceptions;

public class ValidationException extends Exception {
    public ValidationException(String message) {
        super("Ошибка при валидации данных: " + message);
    }
}
