package utils.generators;

import data.Coordinates;
import exceptions.ObjectCreationException;
import java.util.Objects;

public class CoordinatesGenerator extends ObjectGenerator<Coordinates> {
  @Override
  public Coordinates create(boolean fileMode) throws ObjectCreationException {
    if (!fileMode) {
      System.out.println("Добро пожаловать в Формировать координат.");
    }
    return new Coordinates(
        askFloat("Координата по X (float, not null): ", Objects::nonNull, fileMode),
        askLong(
            "Координата по Y (Long, not null, <=332): ", x -> (x != null && x <= 332), fileMode));
  }
}
