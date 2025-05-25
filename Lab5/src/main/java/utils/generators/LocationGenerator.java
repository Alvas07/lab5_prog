package utils.generators;

import data.Location;
import exceptions.ObjectCreationException;
import java.util.Objects;

/**
 * Класс, отвечающий за запрос необходимых данных от пользователя и генерацию объекта класса {@link
 * Location}.
 *
 * @see Location
 * @author Alvas
 * @since 1.0
 */
public class LocationGenerator extends ObjectGenerator<Location> {
  /**
   * Генерирует объект класса {@link Location}, запрашивая от пользователя значения полей.
   *
   * @return Объект класса {@link Location}.
   * @throws ObjectCreationException если происходит ошибка при создании объекта класса {@link
   *     Location}.
   * @author Alvas
   * @see Location
   * @since 1.0
   */
  @Override
  public Location create() throws ObjectCreationException {
    String choice =
        askValue(
                "Добавить местоположение? (1 - Да, 2 - Нет): ",
                s ->
                    (s.equals("1")
                        || s.equals("2")
                        || s.equalsIgnoreCase("да")
                        || s.equalsIgnoreCase("нет")),
                s -> s)
            .toLowerCase();
    return switch (choice) {
      case "1", "да" -> {
        System.out.println("Добро пожаловать в Формирователь местоположения.");
        yield new Location(
            askValue("Местоположение по X (Long, not null): ", Objects::nonNull, Long::parseLong),
            askValue("Местоположение по Y (Long, not null): ", Objects::nonNull, Long::parseLong),
            askValue(
                "Местоположение по Z (Integer, not null): ", Objects::nonNull, Integer::parseInt));
      }
      case "2", "нет" -> null;
      default -> throw new ObjectCreationException("Некорректный выбор.");
    };
  }
}
