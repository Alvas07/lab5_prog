package managers;

import exceptions.FileReadException;
import exceptions.UnknownCommandException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import managers.commands.Command;

/**
 * Класс, отвечающий за связь между пользователем и командами {@link CommandManager}.
 *
 * <p>Производит чтение коллекции из исходного файла с помощью {@link FileManager}.
 *
 * <p>Затем читает очередную команду {@link Command} из командной строки до момента завершения
 * работы программы.
 *
 * @see Command
 * @see CommandManager
 * @see FileManager
 * @author Alvas
 * @since 1.0
 */
public class Console {
  /**
   * Запускает работу приложения.
   *
   * @param args путь до файла с коллекцией.
   * @author Alvas
   * @since 1.0
   */
  public void start(String[] args) {
    Scanner scanner = ScannerManager.getScanner();
    CollectionManager collectionManager = new CollectionManager();
    FileManager fileManager = new FileManager(args[0], collectionManager);
    CommandManager commandManager = new CommandManager(collectionManager, fileManager);
    try {
      System.out.println("Загрузка информации о коллекции из файла...");
      fileManager.fillCollectionFromXml();
      System.out.println("Загрузка прошла успешно!");
    } catch (FileReadException e) {
      System.out.println(e.getMessage());
      System.exit(0);
    }

    System.out.println("Добро пожаловать в приложение для управления коллекцией билетов!");
    System.out.println("Для справки введите: help");
    try {
      while (true) {
        String command = scanner.nextLine().trim();
        if (!command.isEmpty()) {
          try {
            commandManager.startExecuting(command);
          } catch (UnknownCommandException e) {
            System.out.println(e.getMessage());
          }
        }
      }
    } catch (NoSuchElementException e) {
      System.out.println("Нажата комбинация CTRL+D. Завершение работы программы.");
      System.exit(0);
    }
  }
}
