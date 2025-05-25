package utils.generators;

import exceptions.ObjectCreationException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Predicate;
import managers.ScriptManager;

/**
 * Абстрактный класс для запроса необходимых данных от пользователя и генерации объектов класса
 * {@link T}.
 *
 * @param <T> класс генерируемого объекта.
 * @author Alvas
 * @since 1.0
 */
public abstract class ObjectGenerator<T> {
  private Scanner scanner;

  /**
   * Абстрактный метод для генерации объекта класса {@link T}.
   *
   * @return Объект класса {@link T}.
   * @throws ObjectCreationException если происходит ошибка при создании объекта класса {@link T}.
   * @author Alvas
   * @since 1.0
   */
  public abstract T create() throws ObjectCreationException;

  /**
   * Запрашивает у пользователя значение типа {@link T}, пока не получит проходящее валидацию.
   *
   * <p>Отдельно обрабатывает запрос в режиме выполнения скрипта.
   *
   * @param prompt сообщение с подсказкой.
   * @param validator предикат валидации.
   * @param parser функция-парсер.
   * @return Корректное значение.
   * @param <T> тип возвращаемого значения.
   * @throws ObjectCreationException если в скрипте находится значение, не проходящее валидацию.
   * @author Alvas
   * @since 2.0
   */
  public <T> T askValue(String prompt, Predicate<T> validator, Function<String, T> parser)
      throws ObjectCreationException {
    boolean fileMode = ScriptManager.getFileMode();
    while (true) {
      if (fileMode) {
        try {
          scanner = ScriptManager.getLastScanner();
          if (!scanner.hasNextLine()) {
            throw new NoSuchElementException();
          }
        } catch (NoSuchElementException e) {
          ScriptManager.removePath();
          if (ScriptManager.getAllScanners().isEmpty()) {
            scanner = new Scanner(System.in);
            ScriptManager.deactivateFileMode();
            fileMode = ScriptManager.getFileMode();
          } else {
            scanner = ScriptManager.getLastScanner();
            fileMode = ScriptManager.getFileMode();
          }
        }
      } else {
        scanner = new Scanner(System.in);
        fileMode = false;
      }
      System.out.print(prompt);
      String input = scanner.nextLine().trim();
      if (fileMode) {
        System.out.println(input);
      }
      try {
        T value = parser.apply(input);
        if (validator.test(value)) {
          return parser.apply(input);
        } else if (fileMode) {
          throw new ObjectCreationException("Значение не прошло валидацию.");
        } else {
          System.out.println("Значение не прошло валидацию.");
        }
      } catch (NumberFormatException e) {
        if (input.isEmpty() && validator.test(null)) {
          return null;
        }
        if (fileMode) {
          throw new ObjectCreationException("Неверный формат ввода.");
        } else {
          System.out.println("Неверный формат ввода.");
        }
      }
    }
  }

  /**
   * Запрашивает у пользователя значение перечисления, пока не получит проходящее валидацию.
   *
   * <p>Отдельно обрабатывает запрос в режиме выполнения скрипта.
   *
   * @param prompt сообщение с подсказкой.
   * @param exceptedValues доступные значения перечисления.
   * @param validator предикат валидации.
   * @return Корректное значение перечисления.
   * @throws ObjectCreationException если в скрипте находится значение перечисления, не проходящее
   *     валидацию.
   * @author Alvas
   * @since 1.0
   */
  public Enum askEnum(String prompt, Enum[] exceptedValues, Predicate<String> validator)
      throws ObjectCreationException {
    boolean fileMode = ScriptManager.getFileMode();
    System.out.println("Доступные значения:");
    for (Enum value : exceptedValues) {
      System.out.println(">>> " + value.toString());
    }
    while (true) {
      if (fileMode) {
        try {
          scanner = ScriptManager.getLastScanner();
          if (!scanner.hasNextLine()) {
            throw new NoSuchElementException();
          }
        } catch (NoSuchElementException e) {
          ScriptManager.removePath();
          if (ScriptManager.getAllScanners().isEmpty()) {
            scanner = new Scanner(System.in);
            ScriptManager.deactivateFileMode();
            fileMode = ScriptManager.getFileMode();
          } else {
            scanner = ScriptManager.getLastScanner();
            fileMode = ScriptManager.getFileMode();
          }
        }
      } else {
        scanner = new Scanner(System.in);
        fileMode = false;
      }
      System.out.print(prompt);
      String input = scanner.nextLine().trim();
      if (fileMode) {
        System.out.println(input);
      }
      if (validator.test(input)) {
        for (Enum value : exceptedValues) {
          if (value.toString().equals(input.toUpperCase())) {
            return value;
          }
        }
        if (fileMode) {
          throw new ObjectCreationException("Значение не найдено.");
        } else {
          System.out.println("Значение не найдено.");
        }
      }
      if (fileMode) {
        throw new ObjectCreationException("Неверный формат ввода.");
      } else {
        System.out.println("Неверный формат ввода.");
      }
    }
  }
}
