package utils.comparators;

import data.Ticket;
import java.util.Comparator;

public final class TicketComparator implements Comparator<Ticket> {
  @Override
  public int compare(Ticket ticket1, Ticket ticket2) {
    // Name Compare
    int nameCompare = ticket1.getName().compareTo(ticket2.getName());
    if (nameCompare != 0) return nameCompare;

    // Coordinates X Compare
    int coordinatesXCompare =
        Float.compare(ticket1.getCoordinates().getX(), ticket2.getCoordinates().getX());
    if (coordinatesXCompare != 0) return coordinatesXCompare;

    // Coordinates Y Compare
    int coordinatesYCompare =
        Long.compare(ticket1.getCoordinates().getY(), ticket2.getCoordinates().getY());
    if (coordinatesYCompare != 0) return coordinatesYCompare;

    // Price Compare
    int priceCompare = Float.compare(ticket1.getPrice(), ticket2.getPrice());
    if (priceCompare != 0) return priceCompare;

    // Ticket Type Compare
    int ticketTypeCompare = ticket1.getType().compareTo(ticket2.getType());
    if (ticketTypeCompare != 0) return ticketTypeCompare;

    // Person Compare (null < other)
    if (ticket1.getPerson() == null && ticket2.getPerson() != null) return -1;
    if (ticket1.getPerson() != null && ticket2.getPerson() == null) return 1;
    if (ticket1.getPerson() != null && ticket2.getPerson() != null) {
      // Person Height Compare
      int heightCompare =
          Float.compare(ticket1.getPerson().getHeight(), ticket2.getPerson().getHeight());
      if (heightCompare != 0) return heightCompare;

      // Person Weight Compare
      int weightCompare =
          Integer.compare(ticket1.getPerson().getWeight(), ticket2.getPerson().getWeight());
      if (weightCompare != 0) return weightCompare;

      // Person PassportID Compare (null < other)
      if (ticket1.getPerson().getPassportID() == null
          && ticket2.getPerson().getPassportID() != null) return -1;
      if (ticket1.getPerson().getPassportID() != null
          && ticket2.getPerson().getPassportID() == null) return 1;
      if (ticket1.getPerson().getPassportID() != null
          && ticket2.getPerson().getPassportID() != null) {
        int passportIDCompare =
            ticket1.getPerson().getPassportID().compareTo(ticket2.getPerson().getPassportID());
        if (passportIDCompare != 0) return passportIDCompare;
      }

      // Person Location Compare (null < other)
      if (ticket1.getPerson().getLocation() == null && ticket2.getPerson().getLocation() != null)
        return -1;
      if (ticket1.getPerson().getLocation() != null && ticket2.getPerson().getLocation() == null)
        return 1;
      if (ticket1.getPerson().getLocation() != null && ticket2.getPerson().getLocation() != null) {
        // Location X Compare
        int locationXCompare =
            Long.compare(
                ticket1.getPerson().getLocation().getX(), ticket2.getPerson().getLocation().getX());
        if (locationXCompare != 0) return locationXCompare;

        // Location Y Compare
        int locationYCompare =
            Long.compare(
                ticket1.getPerson().getLocation().getY(), ticket2.getPerson().getLocation().getY());
        if (locationYCompare != 0) return locationYCompare;

        // Location Z Compare
        int locationZCompare =
            Integer.compare(
                ticket1.getPerson().getLocation().getZ(), ticket2.getPerson().getLocation().getZ());
        if (locationZCompare != 0) return locationZCompare;
      }
    }

    // Tickets are equal
    return 0;
  }
}
