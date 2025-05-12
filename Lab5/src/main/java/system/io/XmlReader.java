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

public class XmlReader implements TicketReader {
  @Override
  public List<Ticket> readTickets(String fileName) throws FileReadException {
    if (!canRead(fileName)) {
      throw new FileReadException("Невозможно прочитать файл.");
    }

    List<Ticket> tickets = new ArrayList<>();

    try (InputStreamReader reader =
        new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8)) {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
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
              System.out.println("ВАЛИДАЦИЯ");
              System.out.println(ticket);
              System.out.println(Validator.isValidCoordinates(ticket.getCoordinates()));
              System.out.println(Validator.isValidPerson(ticket.getPerson()));
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

  private LocalDate parseCreationDate(Element element) throws ObjectCreationException {
    try {
      String text = getTextValue(element, "creationDate");
      return LocalDate.parse(text);
    } catch (DateTimeParseException e) {
      throw new ObjectCreationException("Некорректный формат даты создания.");
    }
  }

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
