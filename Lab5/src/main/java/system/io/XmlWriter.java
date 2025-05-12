package system.io;

import data.Coordinates;
import data.Location;
import data.Person;
import data.Ticket;
import exceptions.FileWriteException;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.List;

public class XmlWriter implements TicketWriter {
  private int tabsCount = 0;

  @Override
  public void writeTicketsToFile(String fileName, List<Ticket> tickets) throws FileWriteException {
    if (!canWrite(fileName)) {
      throw new FileWriteException("Невозможно записать в файл.");
    }

    tickets.sort(Comparator.comparingInt(Ticket::getId));
    int tabsCount = 1;
    try (BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(fileName))) {
      String xmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
      writer.write(xmlString.getBytes(StandardCharsets.UTF_8));
      xmlString = "<list>\n";
      writer.write(xmlString.getBytes(StandardCharsets.UTF_8));
      incrementTabs();
      for (Ticket ticket : tickets) {
        writeTicket(writer, ticket);
      }
      decrementTabs();
      xmlString = "</list>\n";
      writer.write(xmlString.getBytes(StandardCharsets.UTF_8));
      writer.flush();
    } catch (IOException e) {
      throw new FileWriteException(e.getMessage());
    }
  }

  @Override
  public boolean canWrite(String fileName) {
    File file = new File(fileName);
    if (!file.exists()) {
      System.out.println("Файл не найден.");
      return false;
    }

    if (!file.isFile()) {
      System.out.println("Указанный путь не является файлом.");
      return false;
    }

    if (!file.canWrite()) {
      System.out.println("Нет прав на запись в файл.");
      return false;
    }

    return true;
  }

  private void writeTicket(BufferedOutputStream writer, Ticket ticket) throws FileWriteException {
    try {
      String xmlString = "\t".repeat(tabsCount) + "<ticket>\n";
      writer.write(xmlString.getBytes(StandardCharsets.UTF_8));
      incrementTabs();
      writeElement(writer, "id", ticket.getId());
      writeElement(writer, "name", ticket.getName());
      writeCoordinates(writer, ticket.getCoordinates());
      writeElement(writer, "creationDate", ticket.getCreationDate());
      writeElement(writer, "price", ticket.getPrice());
      writeElement(writer, "type", ticket.getType());
      writePerson(writer, ticket.getPerson());
      decrementTabs();
      xmlString = "\t".repeat(tabsCount) + "</ticket>\n";
      writer.write(xmlString.getBytes(StandardCharsets.UTF_8));
      writer.flush();
    } catch (IOException e) {
      throw new FileWriteException(e.getMessage());
    }
  }

  private void writeElement(BufferedOutputStream writer, String tagName, Object value)
      throws FileWriteException {
    try {
      if (value == null) {
        value = "";
      }
      String xmlString =
          "\t".repeat(tabsCount) + "<" + tagName + ">" + value + "</" + tagName + ">\n";
      writer.write(xmlString.getBytes(StandardCharsets.UTF_8));
      writer.flush();
    } catch (IOException e) {
      throw new FileWriteException(e.getMessage());
    }
  }

  private void writeCoordinates(BufferedOutputStream writer, Coordinates coordinates)
      throws FileWriteException {
    try {
      String xmlString = "\t".repeat(tabsCount) + "<coordinates>\n";
      writer.write(xmlString.getBytes(StandardCharsets.UTF_8));
      incrementTabs();
      writeElement(writer, "cx", coordinates.getX());
      writeElement(writer, "cy", coordinates.getY());
      decrementTabs();
      xmlString = "\t".repeat(tabsCount) + "</coordinates>\n";
      writer.write(xmlString.getBytes(StandardCharsets.UTF_8));
      writer.flush();
    } catch (IOException e) {
      throw new FileWriteException(e.getMessage());
    }
  }

  private void writeLocation(BufferedOutputStream writer, Location location)
      throws FileWriteException {
    try {
      String xmlString = "\t".repeat(tabsCount) + "<location>\n";
      writer.write(xmlString.getBytes(StandardCharsets.UTF_8));
      incrementTabs();
      writeElement(writer, "lx", location.getX());
      writeElement(writer, "ly", location.getY());
      writeElement(writer, "lz", location.getZ());
      decrementTabs();
      xmlString = "\t".repeat(tabsCount) + "</location>\n";
      writer.write(xmlString.getBytes(StandardCharsets.UTF_8));
      writer.flush();
    } catch (IOException e) {
      throw new FileWriteException(e.getMessage());
    }
  }

  private void writePerson(BufferedOutputStream writer, Person person) throws FileWriteException {
    try {
      String xmlString = "\t".repeat(tabsCount) + "<person>\n";
      writer.write(xmlString.getBytes(StandardCharsets.UTF_8));
      incrementTabs();
      writeElement(writer, "height", person.getHeight());
      writeElement(writer, "weight", person.getWeight());
      writeElement(writer, "passportID", person.getPassportID());
      writeLocation(writer, person.getLocation());
      decrementTabs();
      xmlString = "\t".repeat(tabsCount) + "</person>\n";
      writer.write(xmlString.getBytes(StandardCharsets.UTF_8));
      writer.flush();
    } catch (IOException e) {
      throw new FileWriteException(e.getMessage());
    }
  }

  private void incrementTabs() {
    tabsCount += 1;
  }

  private void decrementTabs() {
    tabsCount -= 1;
  }
}
