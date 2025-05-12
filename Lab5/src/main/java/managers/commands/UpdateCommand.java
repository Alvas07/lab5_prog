package managers.commands;

import data.Ticket;
import exceptions.CommandExecuteException;
import exceptions.ObjectCreationException;
import managers.CollectionManager;
import managers.ScriptManager;
import utils.generators.TicketGenerator;

public class UpdateCommand implements Command {
  private final CollectionManager collectionManager;

  public UpdateCommand(CollectionManager collectionManager) {
    this.collectionManager = collectionManager;
  }

  @Override
  public void execute(String[] args) throws CommandExecuteException {
    if (args.length != 2) {
      throw new CommandExecuteException("Команда принимает один обязательный аргумент.");
    }

    try {
      int id = Integer.parseInt(args[1]);
      Ticket ticket = new TicketGenerator().create(ScriptManager.getFileMode());
      collectionManager.updateTicket(id, ticket);
      System.out.println("Элемент с id=" + id + " обновлен.");
    } catch (NumberFormatException | ObjectCreationException e) {
      System.out.println("id должен быть целым числом.");
    }
  }

  @Override
  public String getName() {
    return "update";
  }

  @Override
  public String getDescription() {
    return "обновить элемент по id";
  }
}
