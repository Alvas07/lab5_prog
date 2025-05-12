package managers.commands;

import exceptions.CommandExecuteException;
import managers.CollectionManager;

public class AverageOfPriceCommand implements Command {
  private final CollectionManager collectionManager;

  public AverageOfPriceCommand(CollectionManager collectionManager) {
    this.collectionManager = collectionManager;
  }

  @Override
  public void execute(String[] args) throws CommandExecuteException {
    if (args.length != 1) {
      throw new CommandExecuteException("Команда не принимает аргументы.");
    }

    System.out.println(
        "Cредняя цена по всем элементам коллекции равна " + collectionManager.getAveragePrice());
  }

  @Override
  public String getName() {
    return "average_of_price";
  }

  @Override
  public String getDescription() {
    return "вывести среднее значение поля price для всех элементов коллекции";
  }
}
