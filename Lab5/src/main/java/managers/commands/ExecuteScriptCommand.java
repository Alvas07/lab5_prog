package managers.commands;

import exceptions.CommandExecuteException;
import exceptions.UnknownCommandException;
import managers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ExecuteScriptCommand implements Command {
    private CollectionManager collectionManager;

    public ExecuteScriptCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) throws CommandExecuteException {
        if (args.length != 2) {
            throw new CommandExecuteException("Команда принимает один обязательный аргумент.");
        }

        String fileName = args[0];
        FileManager fileManager = new FileManager(fileName, collectionManager);
        CommandManager commandManager = new CommandManager(collectionManager);
        if (!fileManager.canRead()) {
            throw new CommandExecuteException("Невозможно прочитать информацию из файла.");
        }

        try {
            ScriptManager.addPath(fileName);
            Scanner currentScanner;

            while ((currentScanner = ScriptManager.getLastScanner()) != null) {
                ScannerManager.setScanner(currentScanner);
                String input = currentScanner.nextLine();
                String[] commandParts = input.trim().split(" ");

                if (commandParts[0].equalsIgnoreCase("execute_script") && ScriptManager.isRecursive(commandParts[1])) {
                    System.out.println("Обнаружена рекурсия! Повторно вызывается файл " + new File(commandParts[1]).getAbsolutePath());
                    continue;
                }

                System.out.println("Выполнение команды " + commandParts[0] + ":");
                try {
                    commandManager.startExecuting(input);
                } catch (UnknownCommandException e) {
                    System.out.println(e.getMessage());
                }
            }

            ScriptManager.removePath();
            ScannerManager.setScanner(new Scanner(System.in));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String getName() {
        return "execute_script";
    }

    @Override
    public String getDescription() {
        return "считать и исполнить скрипт из указанного файла";
    }
}
