package managers.commands;

import exceptions.CommandExecuteException;
import exceptions.FileWriteException;
import managers.CollectionManager;
import managers.Console;
import managers.FileManager;

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
            FileManager fileManager = new FileManager(Console.DATA_PATH, collectionManager);
            fileManager.saveCollectionToXml();
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
