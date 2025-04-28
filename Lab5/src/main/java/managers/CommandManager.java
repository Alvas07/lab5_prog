package managers;

import exceptions.CommandExecuteException;
import exceptions.UnknownCommandException;
import managers.commands.*;

import java.util.LinkedHashMap;

public class CommandManager {
    private final LinkedHashMap<String, Command> commandList;

    public CommandManager(CollectionManager collectionManager) {
        commandList = new LinkedHashMap<>();
        commandList.put("help", new HelpCommand(collectionManager));
        commandList.put("info", new InfoCommand(collectionManager));
        commandList.put("show", new ShowCommand(collectionManager));
        commandList.put("add", new AddCommand(collectionManager));
        commandList.put("update", new UpdateCommand(collectionManager));
        commandList.put("remove_by_id", new RemoveByIdCommand(collectionManager));
        commandList.put("clear", new ClearCommand(collectionManager));
        commandList.put("save", new SaveCommand(collectionManager));
        commandList.put("execute_script", new ExecuteScriptCommand(collectionManager));
        commandList.put("exit", new ExitCommand());
        commandList.put("remove_head", new RemoveHeadCommand(collectionManager));
        commandList.put("remove_lower", new RemoveLowerCommand(collectionManager));
        commandList.put("max_by_creation_date", new MaxByCreationDateCommand(collectionManager));
        commandList.put("filter_by_type", new FilterByTypeCommand(collectionManager));
        commandList.put("add_if_max", new AddIfMaxCommand(collectionManager));
        commandList.put("average_of_price", new AverageOfPriceCommand(collectionManager));
    }

    public void startExecuting(String line) throws UnknownCommandException {
        String commandName = line.strip().split(" ")[0];
        if (!commandList.containsKey(commandName)) {
            throw new UnknownCommandException(commandName);
        }
        Command command = commandList.get(commandName);
        try {
            command.execute(line.strip().split(" "));
        } catch (CommandExecuteException e) {
            System.out.println(e.getMessage());
        }
    }

    public LinkedHashMap<String, Command> getCommandList() {
        return commandList;
    }
}