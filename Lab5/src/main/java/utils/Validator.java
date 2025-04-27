package utils;

import data.*;
import exceptions.ValidationException;
import utils.generators.IdGenerator;

import java.time.DateTimeException;
import java.time.LocalDate;

public class Validator {
    public static boolean isValidTicket(Ticket t) {
        return IdGenerator.idIsUnique(t.getId()) &&

                t.getName() != null &&
                !t.getName().isEmpty() &&

                t.getCoordinates() != null &&
                isValidCoordinates(t.getCoordinates()) &&

                t.getCreationDate() != null &&

                t.getPrice() > 0 &&

                t.getType() != null &&

                isValidPerson(t.getPerson());

    }

    public static boolean isValidCoordinates(Coordinates c) {
        return c.getY() != null &&
                c.getY() <= 332;
    }

    public static boolean isValidLocation(Location l) {
        return l.getX() != null &&
                l.getY() != null &&
                l.getZ() != null;
    }

    public static boolean isValidPerson(Person p) {
        return p.getHeight() != null &&
                p.getHeight() > 0 &&

                p.getWeight() > 0 &&

                p.getPassportID().length() <= 28 &&

                isValidLocation(p.getLocation());
    }
}
