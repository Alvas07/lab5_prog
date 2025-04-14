package managers.commands;

import exceptions.CommandExecuteException;
import exceptions.EmptyCollectionException;
import managers.CollectionManager;

public class MaxByCreationDateCommand implements Command {
    private final CollectionManager collectionManager;

    public MaxByCreationDateCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) throws CommandExecuteException {
        if (args.length != 1) {
            throw new CommandExecuteException("Команда не принимает аргументы.");
        }

        try {
            System.out.println(collectionManager.getMaxByDate());
        } catch (EmptyCollectionException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String getName() {
        return "max_by_creation_date";
    }

    @Override
    public String getDescription() {
        return "вывести максимальный по creationDate элемент коллекции";
    }
}
