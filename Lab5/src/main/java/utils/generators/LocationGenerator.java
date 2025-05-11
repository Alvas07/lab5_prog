package utils.generators;

import data.Location;
import exceptions.ObjectCreationException;

import java.util.Objects;

public class LocationGenerator extends ObjectGenerator<Location> {
    @Override
    public Location create(boolean fileMode) throws ObjectCreationException {
        if (!fileMode) {
            System.out.println("Добро пожаловать в Формирователь местоположения.");
        }
        return new Location(askLong("Местоположение по X (Long, not null):", Objects::nonNull, fileMode),
                askLong("Местоположение по Y (Long, not null):", Objects::nonNull, fileMode),
                askInteger("Местоположение по Z (Integer, not null):", Objects::nonNull, fileMode));
    }
}
