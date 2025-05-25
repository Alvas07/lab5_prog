package utils.generators;

import data.*;
import exceptions.ObjectCreationException;
import java.time.LocalDate;
import managers.IdManager;

/**
 * Класс, отвечающий за запрос необходимых данных от пользователя и генерацию объекта класса {@link
 * Ticket}.
 *
 * @see Ticket
 * @author Alvas
 * @since 1.0
 */
public class TicketGenerator extends ObjectGenerator<Ticket> {
  private final IdManager idManager;

  public TicketGenerator(IdManager idManager) {
    this.idManager = idManager;
  }

  /**
   * Генерирует объект класса {@link Ticket}, запрашивая от пользователя значения полей.
   *
   * @return Объект класса {@link Ticket}.
   * @see Ticket
   * @throws ObjectCreationException если происходит ошибка при создании объекта класса {@link
   *     Ticket}.
   * @author Alvas
   * @since 1.0
   */
  @Override
  public Ticket create() throws ObjectCreationException {
    System.out.println("Добро пожаловать в Формирователь билета.");
    return new Ticket(
        idManager.getAndIncrement(),
        askString("Наименование (string, not null, not empty): ", x -> (x != null && !x.isEmpty())),
        askCoordinates(),
        LocalDate.now(),
        askFloat("Стоимость (float, not null, >0): ", x -> (x != null && x > 0)),
        askTicketType(),
        askPerson());
  }

  /**
   * Запрашивает от пользователя поле {@link Coordinates}.
   *
   * @return Объект класса {@link Coordinates}.
   * @see Coordinates
   * @throws ObjectCreationException если происходит ошибка при создании объекта класса {@link
   *     Coordinates}.
   * @author Alvas
   * @since 1.0
   */
  private Coordinates askCoordinates() throws ObjectCreationException {
    return new CoordinatesGenerator().create();
  }

  /**
   * Запрашивает от пользователя поле {@link Person}.
   *
   * @return Объект класса {@link Person}.
   * @see Person
   * @throws ObjectCreationException если происходит ошибка при создании объекта класса {@link
   *     Person}.
   * @author Alvas
   * @since 1.0
   */
  private Person askPerson() throws ObjectCreationException {
    return new PersonGenerator().create();
  }

  /**
   * Запрашивает от пользователя поле {@link TicketType}.
   *
   * @return Объект перечисления {@link TicketType}.
   * @see TicketType
   * @throws ObjectCreationException если происходит ошибка при создании объекта перечисления {@link
   *     TicketType}.
   * @author Alvas
   * @since 1.0
   */
  private TicketType askTicketType() throws ObjectCreationException {
    return (TicketType) askEnum("Тип билета: ", TicketType.values(), x -> true);
  }
}
