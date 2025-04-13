package managers.commands;

import managers.CollectionManager;

public class ShowCommand implements Command {
    private final CollectionManager collectionManager;

    public ShowCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        if (collectionManager.getCollectionSize() == 0) {
            System.out.println("Коллекция пуста.");
        } else {
            collectionManager.getCollection().forEach(System.out::println);
        }
    }

    @Override
    public String getName() {
        return "show";
    }

    @Override
    public String getDescription() {
        return "вывести все элементы коллекции";
    }
}
