package exceptions;

public class FileWriteException extends Exception {
  public FileWriteException(String message) {
    super("Ошибка при записи информации в файл: " + message);
  }
}
