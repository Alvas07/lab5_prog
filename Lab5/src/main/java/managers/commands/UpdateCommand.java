package managers.commands;

import data.Ticket;
import exceptions.CommandExecuteException;
import managers.CollectionManager;
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
            Ticket ticket = TicketGenerator.createTicket();
            collectionManager.updateTicket(id, ticket);
        } catch (NumberFormatException e) {
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
