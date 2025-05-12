package managers.commands;

import data.Ticket;
import exceptions.CommandExecuteException;
import exceptions.ObjectCreationException;
import exceptions.WrongArgumentException;
import managers.CollectionManager;
import managers.ScriptManager;
import utils.generators.TicketGenerator;

public class AddCommand implements Command {
  private final CollectionManager collectionManager;

  public AddCommand(CollectionManager collectionManager) {
    this.collectionManager = collectionManager;
  }

  @Override
  public void execute(String[] args) throws CommandExecuteException {
    if (args.length != 1) {
      throw new CommandExecuteException("Команда не принимает аргументы.");
    }

    try {
      Ticket ticket = new TicketGenerator().create(ScriptManager.getFileMode());
      collectionManager.addTicket(ticket);
      System.out.println("Элемент успешно добавлен.");
    } catch (WrongArgumentException | ObjectCreationException e) {
      System.out.println(e.getMessage());
    }
  }

  @Override
  public String getName() {
    return "add";
  }

  @Override
  public String getDescription() {
    return "добавить новый элемент в коллекцию";
  }
}
