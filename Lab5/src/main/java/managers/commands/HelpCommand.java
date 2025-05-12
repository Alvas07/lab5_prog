package managers.commands;

import exceptions.CommandExecuteException;
import java.util.LinkedHashMap;
import managers.CollectionManager;
import managers.CommandManager;

public class HelpCommand implements Command {
  private final CollectionManager collectionManager;

  public HelpCommand(CollectionManager collectionManager) {
    this.collectionManager = collectionManager;
  }

  @Override
  public void execute(String[] args) throws CommandExecuteException {
    CommandManager commandManager = new CommandManager(collectionManager);
    LinkedHashMap<String, Command> commandList = commandManager.getCommandList();
    for (String commandName : commandList.keySet()) {
      Command command = commandList.get(commandName);
      System.out.println(command.getName() + " - " + command.getDescription());
    }
  }

  @Override
  public String getName() {
    return "help";
  }

  @Override
  public String getDescription() {
    return "вывести справку по доступным командам";
  }
}
