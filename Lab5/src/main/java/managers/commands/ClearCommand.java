package managers.commands;

import exceptions.CommandExecuteException;
import managers.CollectionManager;

public class ClearCommand implements Command {
  private final CollectionManager collectionManager;

  public ClearCommand(CollectionManager collectionManager) {
    this.collectionManager = collectionManager;
  }

  @Override
  public void execute(String[] args) throws CommandExecuteException {
    if (args.length != 1) {
      throw new CommandExecuteException("Команда не принимает аргументы.");
    }

    collectionManager.clearCollection();
    System.out.println("Коллекция очищена.");
  }

  @Override
  public String getName() {
    return "clear";
  }

  @Override
  public String getDescription() {
    return "очистить коллекцию";
  }
}
