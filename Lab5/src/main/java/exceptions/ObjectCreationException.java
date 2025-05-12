package exceptions;

public class ObjectCreationException extends Exception {
  public ObjectCreationException(String message) {
    super("Ошибка при создании объекта: " + message);
  }
}
