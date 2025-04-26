package exceptions;

public class FileReadException extends RuntimeException {
    public FileReadException(String message) {
        super("Ошибка при чтении информации из файла: " + message);
    }
}
