package managers.commands;

import exceptions.NotEnoughArgumentsException;

public interface Command {
    void execute(String[] args) throws NotEnoughArgumentsException;

    String getName();

    String getDescription();
}
