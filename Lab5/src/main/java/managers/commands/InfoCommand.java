package managers.commands;

import exceptions.CommandExecuteException;
import managers.CollectionManager;

public class InfoCommand implements Command {
    private final CollectionManager collectionManager;

    public InfoCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) throws CommandExecuteException {
        if (args.length != 1) {
            throw new CommandExecuteException("Команда не принимает аргументы.");
        }

        System.out.println("ИНФОРМАЦИЯ О КОЛЛЕКЦИИ");
        System.out.println("Тип коллекции: " + collectionManager.getCollection().getClass().getSimpleName());
        System.out.println("Количество элементов: " + collectionManager.getCollectionSize());
        System.out.println("Дата инициализации: " + collectionManager.getInitializationTime());
        System.out.println("Дата последнего изменения: " + collectionManager.getLastUpdateTime());
    }

    @Override
    public String getName() {
        return "info";
    }

    @Override
    public String getDescription() {
        return "вывести информацию о коллекции";
    }
}
