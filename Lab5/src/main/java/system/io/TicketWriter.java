package system.io;

import data.Ticket;
import exceptions.FileWriteException;

import java.util.Collection;

public interface TicketWriter {
    void writeTicketsToFile(String fileName, Collection<Ticket> tickets) throws FileWriteException;

    boolean canWrite(String fileName);
}
