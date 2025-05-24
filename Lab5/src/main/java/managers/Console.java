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
  private boolean isRunning = true;
  private final ScannerManager scannerManager = new ScannerManager(new Scanner(System.in));

  /**
   * Возвращает используемый менеджер сканеров {@link ScannerManager}.
   *
   * @return Менеджер сканеров.
   * @see ScannerManager
   * @author Alvas
   * @since 2.0
   */
  public ScannerManager getScannerManager() {
    return scannerManager;
  }

  /**
   * Показывает, работает ли приложение в данный момент.
   *
   * @return {@code true} - если приложение работает в данный момент, {@code false} - если нет.
   * @author Alvas
   * @since 2.0
   */
  public boolean isRunning() {
    return isRunning;
  }

  /**
   * Завершает работу приложения.
   *
   * @author Alvas
   * @since 2.0
   */
  public void stop() {
    isRunning = false;
  }

  /**
   * Запускает работу приложения.
   *
   * @param args путь до файла с коллекцией.
   * @author Alvas
   * @since 1.0
   */
  public void start(String[] args) {
    Scanner scanner = scannerManager.getScanner();
    CollectionManager collectionManager = new CollectionManager();
    FileManager fileManager = new FileManager(args[0], collectionManager);
    CommandManager commandManager = new CommandManager(collectionManager, fileManager, this);
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
      while (isRunning) {
        System.out.print("> ");
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
      stop();
    }
  }
}
