package managers.commands;

import exceptions.CommandExecuteException;
import managers.CollectionManager;

public class ShowCommand implements Command {
  private final CollectionManager collectionManager;

  public ShowCommand(CollectionManager collectionManager) {
    this.collectionManager = collectionManager;
  }

  @Override
  public void execute(String[] args) throws CommandExecuteException {
    if (args.length != 1) {
      throw new CommandExecuteException("Команда не принимает аргументы.");
    }

    if (collectionManager.getCollectionSize() == 0) {
      System.out.println("Коллекция пуста.");
    } else {
      collectionManager.getCollection().forEach(System.out::println);
    }
  }

  @Override
  public String getName() {
    return "show";
  }

  @Override
  public String getDescription() {
    return "вывести все элементы коллекции";
  }
}
