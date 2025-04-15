package managers.commands;

import data.Ticket;
import data.TicketType;
import exceptions.CommandExecuteException;
import managers.CollectionManager;

import java.util.List;

public class FilterByTypeCommand implements Command {
    private final CollectionManager collectionManager;

    public FilterByTypeCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) throws CommandExecuteException {
        if (args.length != 2) {
            throw new CommandExecuteException("Команда принимает один обязательный аргумент.");
        }

        try {
            TicketType type = TicketType.valueOf(args[1]);
            List<Ticket> filteredTickets = collectionManager.getFilteredByType(type);
            if (filteredTickets.isEmpty()) {
                System.out.println("Элементов, соответствующих данному типу, не найдено.");
            } else {
                System.out.println("ЭЛЕМЕНТЫ С ТИПОМ БИЛЕТА " + type.name().toUpperCase() + ":");
                filteredTickets.forEach(System.out::println);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Такого типа билета не существует.");
        }
    }

    @Override
    public String getName() {
        return "filter_by_type";
    }

    @Override
    public String getDescription() {
        return "вывести элементы с заданным значением type";
    }
}
