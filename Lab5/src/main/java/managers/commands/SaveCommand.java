package managers.commands;

import exceptions.CommandExecuteException;
import exceptions.FileWriteException;
import managers.CollectionManager;

public class SaveCommand implements Command {
    private final CollectionManager collectionManager;

    public SaveCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) throws CommandExecuteException {
        if (args.length != 1) {
            throw new CommandExecuteException("Команда не принимает аргументы.");
        }

        try {
            collectionManager.saveCollection();
        } catch (FileWriteException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public String getName() {
        return "save";
    }

    @Override
    public String getDescription() {
        return "сохранить коллекцию в файл";
    }
}
