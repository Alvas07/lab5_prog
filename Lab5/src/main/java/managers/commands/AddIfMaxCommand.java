package managers.commands;

import data.Ticket;
import exceptions.CommandExecuteException;
import exceptions.ObjectCreationException;
import exceptions.WrongArgumentException;
import managers.CollectionManager;
import managers.ScriptManager;
import utils.generators.IdGenerator;
import utils.generators.TicketGenerator;

public class AddIfMaxCommand implements Command {
  private final CollectionManager collectionManager;

  public AddIfMaxCommand(CollectionManager collectionManager) {
    this.collectionManager = collectionManager;
  }

  @Override
  public void execute(String[] args) throws CommandExecuteException {
    if (args.length != 1) {
      throw new CommandExecuteException("Команда не принимает аргументы.");
    }

    Ticket maxTicket = collectionManager.getMaxTicket();

    try {
      Ticket ticket = new TicketGenerator().create(ScriptManager.getFileMode());
      if (maxTicket == null || collectionManager.getCollection().isEmpty()) {
        collectionManager.addTicket(ticket);
        System.out.println("Элемент добавлен.");
      } else if (ticket.compareTo(maxTicket) > 0) {
        collectionManager.addTicket(ticket);
        System.out.println("Элемент добавлен.");
      } else {
        System.out.println("Элемент не был добавлен.");
        IdGenerator.getAndDecrement();
      }
    } catch (WrongArgumentException | ObjectCreationException e) {
      System.out.println(e.getMessage());
    }
  }

  @Override
  public String getName() {
    return "add_if_max";
  }

  @Override
  public String getDescription() {
    return "добавить элемент, если его значение превышает максимальное из коллекции";
  }
}
