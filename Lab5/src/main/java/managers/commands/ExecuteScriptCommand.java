package managers.commands;

import exceptions.CommandExecuteException;
import exceptions.UnknownCommandException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import managers.*;

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

    String fileName = args[1];
    FileManager fileManager = new FileManager(fileName, collectionManager);
    CommandManager commandManager = new CommandManager(collectionManager);
    if (!fileManager.canRead()) {
      throw new CommandExecuteException("Невозможно прочитать информацию из файла.");
    }

    try {
      ScriptManager.activateFileMode();
      ScriptManager.addPath(fileName);
      Scanner currentScanner;

      while ((currentScanner = ScriptManager.getLastScanner()) != null) {
        ScannerManager.setScanner(currentScanner);
        String input = currentScanner.nextLine();
        String[] commandParts = input.trim().split(" ");

        if (commandParts[0].equalsIgnoreCase("execute_script")
            && ScriptManager.isRecursive(commandParts[1])) {
          System.out.println(
              "Обнаружена рекурсия! Отмена скрипта! Повторно вызывается файл "
                  + new File(commandParts[1]).getAbsolutePath());
          continue;
        }

        System.out.println("Выполнение команды " + commandParts[0] + ":");
        try {
          commandManager.startExecuting(input);
        } catch (UnknownCommandException e) {
          System.out.println(e.getMessage());
        }
      }
    } catch (FileNotFoundException e) {
      System.out.println(e.getMessage());
    } catch (NoSuchElementException e) {
      System.out.println("Скрипт выполнен!");
      ScriptManager.removePath();
      ScannerManager.setScanner(new Scanner(System.in));
      ScriptManager.deactivateFileMode();
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
