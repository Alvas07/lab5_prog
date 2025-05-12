package exceptions;

public class FileReadException extends Exception {
  public FileReadException(String message) {
    super("Ошибка при чтении информации из файла: " + message);
  }
}
