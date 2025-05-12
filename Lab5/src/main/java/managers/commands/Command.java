package managers.commands;

import exceptions.CommandExecuteException;

public interface Command {
  void execute(String[] args) throws CommandExecuteException;

  String getName();

  String getDescription();
}
