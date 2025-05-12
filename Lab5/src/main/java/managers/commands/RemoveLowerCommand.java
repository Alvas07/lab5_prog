package managers.commands;

import data.Ticket;
import exceptions.CommandExecuteException;
import exceptions.ObjectCreationException;
import exceptions.RemoveException;
import managers.CollectionManager;
import managers.ScriptManager;
import utils.generators.TicketGenerator;

public class RemoveLowerCommand implements Command {
  private final CollectionManager collectionManager;

  public RemoveLowerCommand(CollectionManager collectionManager) {
    this.collectionManager = collectionManager;
  }

  @Override
  public void execute(String[] args) throws CommandExecuteException {
    if (args.length != 1) {
      throw new CommandExecuteException("Команда не принимает аргументы.");
    }

    int size = collectionManager.getCollectionSize();
    try {
      Ticket ticket = new TicketGenerator().create(ScriptManager.getFileMode());
      collectionManager.removeLower(ticket);
      System.out.println(
          "Удалено "
              + (size - collectionManager.getCollectionSize())
              + " элементов, меньших заданного.");
    } catch (RemoveException | ObjectCreationException e) {
      System.out.println(e.getMessage());
    }
  }

  @Override
  public String getName() {
    return "remove_lower";
  }

  @Override
  public String getDescription() {
    return "удалить из коллекции все элементы, меньшие заданного";
  }
}
