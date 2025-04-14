package managers.commands;

import data.Ticket;
import exceptions.CommandExecuteException;
import exceptions.WrongArgumentException;
import managers.CollectionManager;
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

        Ticket ticket = TicketGenerator.createTicket();
        try {
            collectionManager.addTicket(ticket);
            System.out.println("Элемент успешно добавлен.");
        } catch (WrongArgumentException e) {
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
