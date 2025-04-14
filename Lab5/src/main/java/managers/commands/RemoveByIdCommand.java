package managers.commands;

import data.Ticket;
import exceptions.CommandExecuteException;
import exceptions.RemoveException;
import exceptions.WrongArgumentException;
import managers.CollectionManager;

public class RemoveByIdCommand implements Command {
    private final CollectionManager collectionManager;

    public RemoveByIdCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) throws CommandExecuteException {
        if (args.length != 2) {
            throw new CommandExecuteException("Команда принимает один обязательный аргумент.");
        }

        try {
            int id = Integer.parseInt(args[1]);
            Ticket ticket = collectionManager.getById(id);
            collectionManager.removeTicket(ticket);
        } catch (WrongArgumentException | NumberFormatException | RemoveException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String getName() {
        return "remove_by_id";
    }

    @Override
    public String getDescription() {
        return "удалить элемент по id";
    }
}
