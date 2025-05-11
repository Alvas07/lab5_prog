package utils.generators;

import data.Location;
import data.Person;
import exceptions.ObjectCreationException;


public class PersonGenerator extends ObjectGenerator<Person> {
    @Override
    public Person create(boolean fileMode) throws ObjectCreationException {
        if (!fileMode) {
            System.out.println("Добро пожаловать в Формирователь пассажира.");
        }
        return new Person(askFloat("Рост (float, not null, >0):", x -> (x != null && x > 0), fileMode),
                askInteger("Вес (int, not null, >0):", x -> (x != null && x > 0), fileMode),
                askString("Номер паспорта (string, len<=28):", x -> x.length() <= 28, fileMode),
                askLocation(fileMode));
    }

    private Location askLocation(boolean fileMode) throws ObjectCreationException {
        return new LocationGenerator().create(fileMode);
    }
}
