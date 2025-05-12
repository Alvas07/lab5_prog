package managers;

import data.Ticket;
import exceptions.FileReadException;
import exceptions.FileWriteException;
import exceptions.WrongArgumentException;
import java.util.List;
import system.io.XmlReader;
import system.io.XmlWriter;

public class FileManager {
  private final String fileName;
  private final CollectionManager collectionManager;
  private final XmlReader reader = new XmlReader();
  private final XmlWriter writer = new XmlWriter();

  public FileManager(String fileName, CollectionManager collectionManager) {
    this.fileName = fileName;
    this.collectionManager = collectionManager;
  }

  public boolean canRead() {
    return reader.canRead(fileName);
  }

  public boolean canWrite() {
    return writer.canWrite(fileName);
  }

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

  public void saveCollectionToXml() throws FileWriteException {
    writer.writeTicketsToFile(fileName, collectionManager.getTicketsList());
  }
}
