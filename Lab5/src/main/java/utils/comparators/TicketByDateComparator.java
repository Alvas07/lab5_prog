package utils.comparators;

import data.Ticket;

import java.util.Comparator;

public class TicketByDateComparator implements Comparator<Ticket> {
    @Override
    public int compare(Ticket ticket1, Ticket ticket2) {
        return ticket1.getCreationDate().compareTo(ticket2.getCreationDate());
    }
}
