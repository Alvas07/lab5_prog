package system.io;

import static utils.XmlUtils.*;

import data.*;
import exceptions.FileReadException;
import exceptions.ObjectCreationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import utils.Validator;
import utils.XmlUtils;

/**
 * Класс, отвечающий за чтение данных из формата XML и их преобразование в объекты класса {@link
 * Ticket}.
 *
 * @see Ticket
 * @author Alvas
 * @since 1.0
 */
public class XmlReader implements TicketReader {
  /**
   * Читает данные из файла формата XML и преобразует их в список объектов класса {@link Ticket}.
   *
   * <p>Для чтения данных из файла использует класс {@link InputStreamReader}.
   *
   * <p>Для десериализации полученных данных использует {@code DOM}.
   *
   * @param fileName путь к файлу.
   * @return Список объектов класса {@link Ticket}.
   * @see Ticket
   * @see InputStreamReader
   * @throws FileReadException если невозможно прочитать файл.
   * @author Alvas
   * @since 1.0
   */
  @Override
  public List<Ticket> readTickets(String fileName) throws FileReadException {
    if (!canRead(fileName)) {
      throw new FileReadException("Невозможно прочитать файл.");
    }

    List<Ticket> tickets = new ArrayList<>();

    try (InputStreamReader reader =
        new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8)) {
      DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
      Document document = builder.parse(new FileInputStream(fileName));
      document.getDocumentElement().normalize();

      NodeList ticketNodes = document.getElementsByTagName("ticket");
      for (int i = 0; i < ticketNodes.getLength(); i++) {
        Node node = ticketNodes.item(i);
        if (node.getNodeType() == Node.ELEMENT_NODE) {
          try {
            Element element = (Element) node;
            Ticket ticket = parseTicket(element);
            if (Validator.isValidTicket(ticket)) {
              tickets.add(ticket);
            } else {
              System.out.println("Объект не прошел валидацию.");
            }
          } catch (ObjectCreationException e) {
            throw new FileReadException(e.getMessage());
          }
        }
      }
    } catch (Exception e) {
      throw new FileReadException("Невозможно десериализовать файл.");
    }

    return tickets;
  }

  /**
   * Показывает возможность чтения данных из файла.
   *
   * @param fileName путь к файлу.
   * @return {@code true} - если файл доступен для чтения, {@code false} - если нет.
   * @author Alvas
   * @since 1.0
   */
  @Override
  public boolean canRead(String fileName) {
    File file = new File(fileName);
    if (!file.exists()) {
      System.out.println("Файл не найден.");
      return false;
    }

    if (!file.isFile()) {
      System.out.println("Указанный путь не является файлом.");
      return false;
    }

    if (!file.canRead()) {
      System.out.println("Нет прав на чтение файла.");
      return false;
    }

    return true;
  }

  /**
   * Преобразует XML-элемент {@link Element} в объект класса {@link Ticket}.
   *
   * <p>Использует для преобразования входных данных методы класса {@link XmlUtils}.
   *
   * @param element XML-элемент для преобразования.
   * @return Объект класса {@link Ticket}.
   * @see Element
   * @see Ticket
   * @see XmlUtils
   * @throws ObjectCreationException если формат входных данных полей билета некорректен.
   * @author Alvas
   * @since 1.0
   */
  private Ticket parseTicket(Element element) throws ObjectCreationException {
    try {
      return new Ticket(
          getIntValue(element, "id"),
          getTextValue(element, "name"),
          parseCoordinates(element),
          parseCreationDate(element),
          getFloatValue(element, "price"),
          parseEnum(element, "type", TicketType.class),
          parsePerson(element));
    } catch (NullPointerException | NumberFormatException e) {
      throw new ObjectCreationException("Некорректный формат билета.");
    }
  }

  /**
   * Преобразует XML-элемент {@link Element} в объект класса {@link LocalDate}.
   *
   * <p>Использует для преобразования входных данных методы класса {@link XmlUtils}.
   *
   * @param element XML-элемент для преобразования.
   * @return Объект класса {@link LocalDate}.
   * @see Element
   * @see LocalDate
   * @see XmlUtils
   * @throws ObjectCreationException если формат входных данных даты некорректен.
   * @author Alvas
   * @since 1.0
   */
  private LocalDate parseCreationDate(Element element) throws ObjectCreationException {
    try {
      String text = getTextValue(element, "creationDate");
      return LocalDate.parse(text);
    } catch (DateTimeParseException e) {
      throw new ObjectCreationException("Некорректный формат даты создания.");
    }
  }

  /**
   * Преобразует XML-элемент {@link Element} в объект класса {@link Coordinates}.
   *
   * <p>Использует для преобразования входных данных методы класса {@link XmlUtils}.
   *
   * @param element XML-элемент для преобразования.
   * @return Объект класса {@link Coordinates}.
   * @see Element
   * @see Coordinates
   * @see XmlUtils
   * @throws ObjectCreationException если формат входных данных полей координат некорректен.
   * @author Alvas
   * @since 1.0
   */
  private Coordinates parseCoordinates(Element element) throws ObjectCreationException {
    if (element == null) {
      throw new ObjectCreationException("Отсутствуют координаты.");
    }
    try {
      return new Coordinates(getFloatValue(element, "cx"), getLongValue(element, "cy"));
    } catch (NullPointerException | NumberFormatException e) {
      throw new ObjectCreationException("Некорректный формат координат.");
    }
  }

  /**
   * Преобразует XML-элемент {@link Element} в объект класса {@link Location}.
   *
   * <p>Использует для преобразования входных данных методы класса {@link XmlUtils}.
   *
   * @param element XML-элемент для преобразования.
   * @return Объект класса {@link Location}.
   * @see Element
   * @see Location
   * @see XmlUtils
   * @throws ObjectCreationException если формат входных данных полей местоположения некорректен.
   * @author Alvas
   * @since 1.0
   */
  private Location parseLocation(Element element) throws ObjectCreationException {
    if (element == null) {
      return null;
    }
    try {
      return new Location(
          getLongValue(element, "lx"), getLongValue(element, "ly"), getIntValue(element, "lz"));
    } catch (NullPointerException | NumberFormatException e) {
      throw new ObjectCreationException("Некорректный формат местоположения.");
    }
  }

  /**
   * Преобразует XML-элемент {@link Element} в объект класса {@link Person}.
   *
   * <p>Использует для преобразования входных данных методы класса {@link XmlUtils}.
   *
   * @param element XML-элемент для преобразования.
   * @return Объект класса {@link Person}.
   * @see Element
   * @see Person
   * @see XmlUtils
   * @throws ObjectCreationException если формат входных данных полей пассажира некорректен.
   * @author Alvas
   * @since 1.0
   */
  private Person parsePerson(Element element) throws ObjectCreationException {
    if (element == null) {
      return null;
    }
    try {
      return new Person(
          getFloatValue(element, "height"),
          getIntValue(element, "weight"),
          getTextValue(element, "passportID"),
          parseLocation(element));
    } catch (NullPointerException | NumberFormatException e) {
      throw new ObjectCreationException("Некорректный формат пассажира.");
    }
  }

  /**
   * Преобразует XML-элемент в перечисление {@link Enum}.
   *
   * <p>Использует для преобразования входных данных методы класса {@link XmlUtils}.
   *
   * @param element XML-элемент для преобразования.
   * @param tagName имя XML-тега, соответствующего перечислению.
   * @param enumClass класс перечисления.
   * @return Перечисление {@link Enum}.
   * @param <T> тип перечисления.
   * @see Enum
   * @see XmlUtils
   * @throws ObjectCreationException если формат входных данных типа некорректен.
   * @author Alvas
   * @since 1.0
   */
  private <T extends Enum<T>> T parseEnum(Element element, String tagName, Class<T> enumClass)
      throws ObjectCreationException {
    try {
      String text = getTextValue(element, tagName);
      return Enum.valueOf(enumClass, text);
    } catch (IllegalArgumentException e) {
      throw new ObjectCreationException("Некорректный формат типа.");
    }
  }
}
