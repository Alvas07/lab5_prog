package managers.commands;

import data.Ticket;
import exceptions.CommandExecuteException;
import exceptions.ObjectCreationException;
import managers.CollectionManager;
import managers.IdManager;
import utils.generators.TicketGenerator;

/**
 * Класс, отвечающий за команду "update".
 *
 * <p>Описание команды: "Обновить элемент {@link Ticket} из коллекции по {@code id}".
 *
 * <p>Принимает на вход один обязательный аргумент - {@code id} элемента в коллекции (тип {@code
 * int}).
 *
 * @see Command
 * @see Ticket
 * @author Alvas
 * @since 1.0
 */
public class UpdateCommand implements Command {
  private final CollectionManager collectionManager;

  /**
   * Конструктор команды.
   *
   * @param collectionManager менеджер коллекции.
   * @see CollectionManager
   * @author Alvas
   * @since 1.0
   */
  public UpdateCommand(CollectionManager collectionManager) {
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
    if (args.length != 2) {
      throw new CommandExecuteException("Команда принимает один обязательный аргумент.");
    }

    try {
      int id = Integer.parseInt(args[1]);
      Ticket ticket = new TicketGenerator().create();
      collectionManager.updateTicket(id, ticket);
      System.out.println("Элемент с id=" + id + " обновлен.");
      IdManager.removeLastId();
    } catch (NumberFormatException | ObjectCreationException e) {
      System.out.println("id должен быть целым числом.");
      IdManager.removeLastId();
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
    return "update";
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
    return "обновить элемент по id";
  }
}
