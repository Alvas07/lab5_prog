package managers;

import data.Ticket;
import exceptions.FileReadException;
import exceptions.FileWriteException;
import exceptions.WrongArgumentException;
import java.util.List;
import system.io.XmlReader;
import system.io.XmlWriter;

/**
 * Класс, отвечающий за взаимодействие с файлами.
 *
 * <p>Осуществляет работу с файлами формата XML с помощью классов {@link XmlReader} и {@link
 * XmlWriter}.
 *
 * @see XmlReader
 * @see XmlWriter
 * @author Alvas
 * @since 1.0
 */
public class FileManager {
  private final String fileName;
  private final CollectionManager collectionManager;
  private final XmlReader reader = new XmlReader();
  private final XmlWriter writer = new XmlWriter();

  /**
   * Конструктор файлового менеджера.
   *
   * @param fileName путь к файлу.
   * @param collectionManager менеджер коллекции {@link CommandManager}.
   * @see CollectionManager
   * @author Alvas
   * @since 1.0
   */
  public FileManager(String fileName, CollectionManager collectionManager) {
    this.fileName = fileName;
    this.collectionManager = collectionManager;
  }

  /**
   * Возвращает путь до используемого файла.
   *
   * @return Путь до используемого файла.
   * @author Alvas
   * @since 2.0
   */
  public String getFileName() {
    return fileName;
  }

  /**
   * Показывает возможность чтения из файла.
   *
   * @return {@code true} - если файл доступен для чтения, {@code false} - если нет.
   * @author Alvas
   * @since 1.0
   */
  public boolean canRead() {
    return reader.canRead(fileName);
  }

  /**
   * Показывает возможность записи в файл.
   *
   * @return {@code true} - если файл доступен для записи, {@code false} - если нет.
   * @author Alvas
   * @since 1.0
   */
  public boolean canWrite() {
    return writer.canWrite(fileName);
  }

  /**
   * Заполняет коллекцию {@link CollectionManager} данными из файла формата XML с помощью {@link
   * XmlReader}.
   *
   * @throws FileReadException если файл невозможно прочитать.
   * @see CollectionManager
   * @see XmlReader
   * @author Alvas
   * @since 1.0
   */
  public void fillCollectionFromXml() throws FileReadException {
    List<Ticket> tickets = reader.readTickets(fileName);
    for (Ticket ticket : tickets) {
      try {
        collectionManager.addTicket(ticket);
      } catch (WrongArgumentException e) {
        System.out.println(e.getMessage());
      }
    }
  }

  /**
   * Сохраняет коллекцию {@link CollectionManager} в файл формата XML с помощью {@link XmlWriter}.
   *
   * @throws FileWriteException если в файл невозможно записать.
   * @see CollectionManager
   * @see XmlWriter
   * @author Alvas
   * @since 1.0
   */
  public void saveCollectionToXml() throws FileWriteException {
    writer.writeTicketsToFile(fileName, collectionManager.getTicketsList());
  }
}
