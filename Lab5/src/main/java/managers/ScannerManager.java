package managers;

import java.util.Scanner;

/**
 * Класс, отвечающий за взаимодействие со сканерами.
 *
 * <p>По умолчанию устанавливает сканер из командной строки {@link System#in}.
 *
 * @see Scanner
 * @see System#in
 * @author Alvas
 * @since 1.0
 */
public class ScannerManager {
  private static Scanner scanner = new Scanner(System.in);

  /**
   * Возвращает текущий установленный в программе сканер.
   *
   * @return Текущий сканер.
   * @author Alvas
   * @since 1.0
   */
  public static Scanner getScanner() {
    return scanner;
  }

  /**
   * Устанавливает для программы заданный сканер.
   *
   * @param scanner новый сканер.
   * @author Alvas
   * @since 1.0
   */
  public static void setScanner(Scanner scanner) {
    ScannerManager.scanner = scanner;
  }
}
