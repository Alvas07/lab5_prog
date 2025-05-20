package managers.commands;

import data.Ticket;
import exceptions.CommandExecuteException;
import exceptions.ObjectCreationException;
import exceptions.WrongArgumentException;
import managers.CollectionManager;
import managers.IdManager;
import utils.generators.TicketGenerator;

/**
 * Класс, отвечающий за команду "add_if_max".
 *
 * <p>Описание команды: "Добавить новый элемент {@link Ticket} в коллекцию, если его значение
 * превышает максимальное из коллекции".
 *
 * <p>Не принимает входных аргументов. Вызывает {@link TicketGenerator}, запрашивающий входные
 * данные для создания нового элемента.
 *
 * @see Command
 * @see Ticket
 * @see TicketGenerator
 * @author Alvas
 * @since 1.0
 */
public class AddIfMaxCommand implements Command {
  private final CollectionManager collectionManager;

  /**
   * Конструктор команды.
   *
   * @param collectionManager менеджер коллекции.
   * @see CollectionManager
   * @author Alvas
   * @since 1.0
   */
  public AddIfMaxCommand(CollectionManager collectionManager) {
    this.collectionManager = collectionManager;
  }

  /**
   * Исполняет команду с заданными параметрами.
   *
   * @param args аргументы команды.
   * @throws CommandExecuteException если указано неверное количество аргументов команды.
   * @author Alvas
   * @since 1.0
   */
  @Override
  public void execute(String[] args) throws CommandExecuteException {
    if (args.length != 1) {
      throw new CommandExecuteException("Команда не принимает аргументы.");
    }

    Ticket maxTicket = collectionManager.getMaxTicket();

    try {
      Ticket ticket = new TicketGenerator().create();
      if (maxTicket == null || collectionManager.getCollection().isEmpty()) {
        collectionManager.addTicket(ticket);
        System.out.println("Элемент добавлен.");
      } else if (ticket.compareTo(maxTicket) > 0) {
        collectionManager.addTicket(ticket);
        System.out.println("Элемент добавлен.");
      } else {
        System.out.println("Элемент не был добавлен.");
        IdManager.removeLastId();
      }
    } catch (WrongArgumentException | ObjectCreationException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * Возвращает название команды.
   *
   * @return Название команды.
   * @author Alvas
   * @since 1.0
   */
  @Override
  public String getName() {
    return "add_if_max";
  }

  /**
   * Возвращает описание команды.
   *
   * @return Описание команды.
   * @author Alvas
   * @since 1.0
   */
  @Override
  public String getDescription() {
    return "добавить элемент, если его значение превышает максимальное из коллекции";
  }
}
