package managers;

import data.Ticket;
import data.TicketType;
import exceptions.EmptyCollectionException;
import exceptions.FileWriteException;
import exceptions.RemoveException;
import exceptions.WrongArgumentException;
import system.io.XmlWriter;
import utils.DateTimeUtils;

import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.List;

public class CollectionManager {
    private final ArrayDeque<Ticket> collection;
    private final LocalDateTime initializationTime;
    private LocalDateTime lastUpdateTime;

    public CollectionManager() {
        this.collection = new ArrayDeque<>();
        this.initializationTime = DateTimeUtils.getStartTime();
        this.lastUpdateTime = DateTimeUtils.getCurrentTime();
    }

    public ArrayDeque<Ticket> getCollection() {
        return collection;
    }

    public LocalDateTime getInitializationTime() {
        return initializationTime;
    }

    public LocalDateTime getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void updateLastModifiedTime() {
        lastUpdateTime = DateTimeUtils.getCurrentTime();
    }

    public int getCollectionSize() {
        return collection.size();
    }

    public void clearCollection() {
        collection.clear();
        updateLastModifiedTime();
    }

    public void addTicket(Ticket ticket) throws WrongArgumentException {
        if (ticket == null) {
            throw new WrongArgumentException("Билет не может быть null.");
        }
        if (collection.contains(ticket)) {
            throw new WrongArgumentException("Билет уже содержится в данной коллекции.");
        }
        collection.addLast(ticket);
        updateLastModifiedTime();
    }

    public Ticket getById(int id) throws WrongArgumentException {
        Ticket ticket = collection.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
        if (ticket == null) {
            throw new WrongArgumentException("Билета с таким id нет в коллекции.");
        }
        return ticket;
    }

    public void updateTicket(int id, Ticket newTicket) {
        try {
            Ticket oldTicket = getById(id);
            oldTicket.setName(newTicket.getName());
            oldTicket.setCoordinates(newTicket.getCoordinates());
            oldTicket.setPrice(newTicket.getPrice());
            oldTicket.setCreationDate(newTicket.getCreationDate());
            oldTicket.setType(newTicket.getType());
            oldTicket.setPerson(newTicket.getPerson());
            updateLastModifiedTime();
        } catch (WrongArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeTicket(Ticket ticket) throws RemoveException {
        if (ticket == null) {
            throw new RemoveException("Удаляемый элемент не может быть null.");
        }
        collection.remove(ticket);
        updateLastModifiedTime();
    }

    public Ticket removeHead() throws RemoveException {
        Ticket head = collection.poll();
        if (head == null) {
            throw new RemoveException("Удаляемый элемент не может быть null.");
        }
        updateLastModifiedTime();
        return head;
    }

    public float getAveragePrice() {
        if (collection.isEmpty()) {
            return (float) 0;
        }

        float sumPrice = 0;
        for (Ticket ticket : collection) {
            sumPrice += ticket.getPrice();
        }
        return sumPrice / getCollectionSize();
    }

    public Ticket getMaxByDate() throws EmptyCollectionException {
        if (collection.isEmpty()) {
            throw new EmptyCollectionException("Невозможно найти максимальный элемент.");
        }

        Ticket ticket = null;
        for (Ticket t : collection) {
            if (ticket == null) {
                ticket = t;
            } else if (ticket.compareToByDate(t) < 0) {
                ticket = t;
            }
        }
        return ticket;
    }

    public List<Ticket> getFilteredByType(TicketType type) {
        return collection.stream().filter(t -> t.getType().equals(type)).toList();
    }

    public Ticket getMaxTicket() {
        return collection.stream().max(Ticket::compareTo).orElse(null);
    }

    public void removeLower(Ticket ticket) throws RemoveException {
        if (ticket == null) {
            throw new RemoveException("Удаляемый элемент не может быть null.");
        }
        collection.removeIf(t -> t.compareTo(ticket) < 0);
        updateLastModifiedTime();
    }
}
