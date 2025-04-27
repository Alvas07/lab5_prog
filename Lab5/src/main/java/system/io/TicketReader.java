package system.io;

import data.Ticket;
import exceptions.FileReadException;

import java.util.List;

public interface TicketReader {
    List<Ticket> readTickets(String fileName) throws FileReadException;

    boolean canRead(String fileName);
}
