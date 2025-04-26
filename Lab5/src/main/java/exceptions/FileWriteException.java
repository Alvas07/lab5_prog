package exceptions;

public class FileWriteException extends RuntimeException {
    public FileWriteException(String message) {
        super("Ошибка при записи информации в файл: " + message);
    }
}
