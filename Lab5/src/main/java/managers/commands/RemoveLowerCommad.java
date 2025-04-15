package managers.commands;

import data.Ticket;
import exceptions.CommandExecuteException;
import exceptions.RemoveException;
import managers.CollectionManager;
import utils.generators.TicketGenerator;

public class RemoveLowerCommad implements Command {
    private final CollectionManager collectionManager;

    public RemoveLowerCommad(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) throws CommandExecuteException {
        if (args.length != 1) {
            throw new CommandExecuteException("Команда не принимает аргументы.");
        }

        int size = collectionManager.getCollectionSize();
        Ticket ticket = TicketGenerator.createTicket();
        try {
            collectionManager.removeLower(ticket);
            System.out.println("Удалено " + (size - collectionManager.getCollectionSize()) + " элементов, меньших заданного.");
        } catch (RemoveException e) {
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
