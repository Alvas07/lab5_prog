package utils.generators;

import data.Location;
import data.Person;
import exceptions.ObjectCreationException;

/**
 * Класс, отвечающий за запрос необходимых данных от пользователя и генерацию объекта класса {@link
 * Person}.
 *
 * @see Person
 * @author Alvas
 * @since 1.0
 */
public class PersonGenerator extends ObjectGenerator<Person> {
  /**
   * Генерирует объект класса {@link Person}, запрашивая от пользователя значения полей.
   *
   * @return Объект класса {@link Person}.
   * @see Person
   * @throws ObjectCreationException если происходит ошибка при создании объекта класса {@link
   *     Person}.
   * @author Alvas
   * @since 1.0
   */
  @Override
  public Person create() throws ObjectCreationException {
    System.out.println("Добро пожаловать в Формирователь пассажира.");
    return new Person(
        askFloat("Рост (float, not null, >0): ", x -> (x != null && x > 0)),
        askInteger("Вес (int, not null, >0): ", x -> (x != null && x > 0)),
        askString("Номер паспорта (string, len<=28): ", x -> x.length() <= 28),
        askLocation());
  }

  /**
   * Запрашивает от пользователя поле {@link Location}.
   *
   * @return Объект класса {@link Location}.
   * @see Location
   * @throws ObjectCreationException если происходит ошибка при создании объекта класса {@link
   *     Location}.
   * @author Alvas
   * @since 1.0
   */
  private Location askLocation() throws ObjectCreationException {
    return new LocationGenerator().create();
  }
}
