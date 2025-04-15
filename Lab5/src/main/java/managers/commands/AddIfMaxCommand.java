package managers.commands;

import data.Ticket;
import exceptions.CommandExecuteException;
import exceptions.WrongArgumentException;
import managers.CollectionManager;
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
        Ticket ticket = TicketGenerator.createTicket();

        try {
            if (maxTicket == null || collectionManager.getCollection().isEmpty()) {
                collectionManager.addTicket(ticket);
            } else if (ticket.compareTo(maxTicket) > 0) {
                collectionManager.addTicket(ticket);
            }
        } catch (WrongArgumentException e) {
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
