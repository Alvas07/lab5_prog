package utils;

import data.Coordinates;
import data.Person;
import data.TicketType;
import exceptions.ValidationException;

public class Validator {
    public static String validateName(String name) throws ValidationException {
        if (name == null || name.trim().isEmpty()) {
            throw new ValidationException("Название билета не может быть пустым.");
        }
        return name;
    }

    public static Coordinates validateCoordinates(Coordinates coordinates) throws ValidationException {
        if (coordinates == null) {
            throw new ValidationException("Координаты не могут быть null.");
        }
        return coordinates;
    }

    public static float validatePrice(float price) throws ValidationException {
        if (price <= 0) {
            throw new ValidationException("Стоимость должна быть больше нуля.");
        }
        return price;
    }

    public static TicketType validateType(TicketType type) throws ValidationException {
        if (type == null) {
            throw new ValidationException("Тип билета не может быть null.");
        }
        return type;
    }

    public static Long validateCoordinatesY(Long y) throws ValidationException {
        if (y == null || y > 332) {
            throw new ValidationException("Координата Y не может быть больше 332 или null.");
        }
        return y;
    }

    public static Long validateLocationX(Long x) throws ValidationException {
        if (x == null) {
            throw new ValidationException("Местоположение по X не может быть null.");
        }
        return x;
    }

    public static Long validateLocationY(Long y) throws ValidationException {
        if (y == null) {
            throw new ValidationException("Местоположение по Y не может быть null.");
        }
        return y;
    }

    public static Integer validateLocationZ(Integer z) throws ValidationException {
        if (z == null) {
            throw new ValidationException("Местоположение по Z не может быть null.");
        }
        return z;
    }

    public static Float validateHeight(Float height) throws ValidationException {
        if (height == null || height <= 0) {
            throw new ValidationException("Рост человека должен быть больше нуля и не быть null.");
        }
        return height;
    }

    public static int validateWeight(int weight) throws ValidationException {
        if (weight <= 0) {
            throw new ValidationException("Вес человека должен быть больше нуля.");
        }
        return weight;
    }

    public static String validatePassportID (String passportID) throws ValidationException {
        if (passportID.length() > 28) {
            throw new ValidationException("Длина паспортых данных не может быть больше 28.");
        }
        return passportID;
    }
}
