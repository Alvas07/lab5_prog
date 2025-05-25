package utils.generators;

import data.Coordinates;
import exceptions.ObjectCreationException;
import java.util.Objects;

/**
 * Класс, отвечающий за запрос необходимых данных от пользователя и генерацию объекта класса {@link
 * Coordinates}.
 *
 * @see Coordinates
 * @author Alvas
 * @since 1.0
 */
public class CoordinatesGenerator extends ObjectGenerator<Coordinates> {
  /**
   * Генерирует объект класса {@link Coordinates}, запрашивая от пользователя значения полей.
   *
   * @return Объект класса {@link Coordinates}.
   * @see Coordinates
   * @throws ObjectCreationException если происходит ошибка при создании объекта класса {@link
   *     Coordinates}.
   * @author Alvas
   * @since 1.0
   */
  @Override
  public Coordinates create() throws ObjectCreationException {
    System.out.println("Добро пожаловать в Формирователь координат.");
    return new Coordinates(
        askValue("Координата по X (float, not null): ", Objects::nonNull, Float::parseFloat),
        askValue(
            "Координата по Y (Long, not null, <=332): ",
            x -> (x != null && x <= 332),
            Long::parseLong));
  }
}
