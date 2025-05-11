package utils.generators;

import data.Coordinates;
import data.Person;
import data.Ticket;
import data.TicketType;
import exceptions.ObjectCreationException;

import java.time.LocalDate;


public class TicketGenerator extends ObjectGenerator<Ticket> {
    @Override
    public Ticket create(boolean fileMode) throws ObjectCreationException {
        if (!fileMode) {
            System.out.println("Добро пожаловать в Формирователь билета.");
        }
        return new Ticket(IdGenerator.getAndIncrement(),
                askString("Наименование (string, not null, not empty):", x -> (x != null && !x.isEmpty()), fileMode),
                askCoordinates(fileMode),
                LocalDate.now(),
                askFloat("Стоимость (float, not null, >0):", x -> (x != null && x > 0), fileMode),
                askTicketType(fileMode),
                askPerson(fileMode));
    }

    private Coordinates askCoordinates(boolean fileMode) throws ObjectCreationException {
        return new CoordinatesGenerator().create(fileMode);
    }

    private Person askPerson(boolean fileMode) throws ObjectCreationException {
        return new PersonGenerator().create(fileMode);
    }

    private TicketType askTicketType(boolean fileMode) throws ObjectCreationException {
        return (TicketType) askEnum("Тип билета:", TicketType.values(), x -> true, fileMode);
    }
}
