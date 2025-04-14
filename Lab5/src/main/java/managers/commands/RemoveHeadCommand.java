package managers.commands;

import data.Ticket;
import exceptions.CommandExecuteException;
import exceptions.RemoveException;
import managers.CollectionManager;

public class RemoveHeadCommand implements Command {
    private final CollectionManager collectionManager;

    public RemoveHeadCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) throws CommandExecuteException {
        if (args.length != 1) {
            throw new CommandExecuteException("Команда не принимает аргументы.");
        }

        try {
            Ticket head = collectionManager.removeHead();
            System.out.println("ПЕРВЫЙ ЭЛЕМЕНТ КОЛЛЕКЦИИ:");
            System.out.println(head);
        } catch (RemoveException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String getName() {
        return "remove_head";
    }

    @Override
    public String getDescription() {
        return "вывести первый элемент коллекции и удалить его";
    }
}
