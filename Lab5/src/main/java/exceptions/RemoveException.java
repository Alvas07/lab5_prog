package exceptions;

public class RemoveException extends Exception {
  public RemoveException(String message) {
    super("Ошибка при удалении элемента коллекции: " + message);
  }
}
