package managers.commands;

import exceptions.CommandExecuteException;
import java.util.LinkedHashMap;
import managers.CollectionManager;
import managers.CommandManager;
import managers.FileManager;

/**
 * Класс, отвечающий за команду "help".
 *
 * <p>Описание команды: "Вывести справку по доступным командам".
 *
 * <p>Не принимает входных аргументов.
 *
 * @see Command
 * @author Alvas
 * @since 1.0
 */
public class HelpCommand implements Command {
  private final CollectionManager collectionManager;

  /**
   * Конструктор команды.
   *
   * @param collectionManager менеджер коллекции.
   * @see CollectionManager
   * @author Alvas
   * @since 1.0
   */
  public HelpCommand(CollectionManager collectionManager) {
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
    CommandManager commandManager =
        new CommandManager(collectionManager, new FileManager("", collectionManager), null);
    LinkedHashMap<String, Command> commandList = commandManager.getCommandList();
    System.out.println("ДОСТУПНЫЕ КОМАНДЫ:");
    for (String commandName : commandList.keySet()) {
      Command command = commandList.get(commandName);
      System.out.println(command.getName() + " - " + command.getDescription());
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
    return "help";
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
    return "вывести справку по доступным командам";
  }
}
