package managers.commands;

import data.Ticket;
import exceptions.WrongArgumentException;
import managers.CollectionManager;
import utils.generators.TicketGenerator;

public class AddCommand implements Command {
    private final CollectionManager collectionManager;

    public AddCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        Ticket ticket = TicketGenerator.createTicket();
        try {
            collectionManager.addTicket(ticket);
            System.out.println("Элемент успешно добавлен.");
        } catch (WrongArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String getName() {
        return "add";
    }

    @Override
    public String getDescription() {
        return "добавить новый элемент в коллекцию";
    }
}
