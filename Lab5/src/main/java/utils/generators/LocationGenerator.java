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
   * @see Location
   * @throws ObjectCreationException если происходит ошибка при создании объекта класса {@link
   *     Location}.
   * @author Alvas
   * @since 1.0
   */
  @Override
  public Location create() throws ObjectCreationException {
    System.out.println("Добро пожаловать в Формирователь местоположения.");
    return new Location(
        askLong("Местоположение по X (Long, not null): ", Objects::nonNull),
        askLong("Местоположение по Y (Long, not null): ", Objects::nonNull),
        askInteger("Местоположение по Z (Integer, not null): ", Objects::nonNull));
  }
}
