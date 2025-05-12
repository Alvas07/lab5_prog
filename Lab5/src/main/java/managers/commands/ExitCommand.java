package managers.commands;

import exceptions.CommandExecuteException;

public class ExitCommand implements Command {
  @Override
  public void execute(String[] args) throws CommandExecuteException {
    if (args.length != 1) {
      throw new CommandExecuteException("Команда не принимает аргументы.");
    }

    System.out.println("Завершение работы програмы.");
    System.exit(0);
  }

  @Override
  public String getName() {
    return "exit";
  }

  @Override
  public String getDescription() {
    return "завершить программу";
  }
}
