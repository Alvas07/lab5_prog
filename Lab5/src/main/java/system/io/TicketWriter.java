package system.io;

import data.Ticket;
import exceptions.FileWriteException;

import java.util.List;

public interface TicketWriter {
    void writeTicketsToFile(String fileName, List<Ticket> tickets) throws FileWriteException;

    boolean canWrite(String fileName);
}
