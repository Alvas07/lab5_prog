package utils;

import data.Coordinates;
import data.Person;
import data.TicketType;
import exceptions.ValidationException;

public class Validator {
    public static void validateName(String arg) throws ValidationException {
        if (arg == null || arg.trim().isEmpty()) {
            throw new ValidationException("Название билета не может быть пустым.");
        }
    }

    public static void validateCoordinates(Coordinates coordinates) throws ValidationException {
        if (coordinates == null) {
            throw new ValidationException("Координаты не могут быть null.");
        }
    }

    public static void validatePrice(String arg) throws ValidationException {
        try {
            float price = Float.parseFloat(arg);
            if (price <= 0) {
                throw new ValidationException("Стоимость должна быть больше нуля.");
            }
        } catch (NumberFormatException e) {
            throw new ValidationException("Стоимость билета должна быть числом типа float.");
        }
    }

    public static void validateType(String arg) throws ValidationException {
        try {
            TicketType.valueOf(arg);
        } catch (IllegalArgumentException e) {
            throw new ValidationException("Неверный параметр. Выберите один из предложенных.");
        }

    }

    public static void validateCoordinatesY(String arg) throws ValidationException {
        try {
            Long y = Long.valueOf(arg);
            if (y > 332) {
                throw new ValidationException("Координата Y не может быть больше 332 или null.");
            }
        } catch (NumberFormatException e) {
            throw new ValidationException("Координата Y должна быть числом типа long.");
        }
    }

    public static void validateLocationX(String arg) throws ValidationException {
        try {
            Long x = Long.valueOf(arg);
        } catch (NumberFormatException e) {
            throw new ValidationException("Местоположение по X должно быть числом типа long.");
        }
    }

    public static void validateLocationY(String arg) throws ValidationException {
        try {
            Long y = Long.valueOf(arg);
        } catch (NumberFormatException e) {
            throw new ValidationException("Местоположение по Y должно быть числом типа long.");
        }
    }

    public static void validateLocationZ(String arg) throws ValidationException {
        try {
            Integer z = Integer.valueOf(arg);
        } catch (NumberFormatException e) {
            throw new ValidationException("Местоположение по Z должно быть числом типа long.");
        }
    }

    public static void validateHeight(String arg) throws ValidationException {
        try {
            Float height = Float.valueOf(arg);
            if (height <= 0) {
                throw new ValidationException("Рост человека должен быть больше нуля.");
            }
        } catch (NumberFormatException e) {
            throw new ValidationException("Рост человека должен быть числом типа float.");
        }
    }

    public static void validateWeight(String arg) throws ValidationException {
        try {
            int weight = Integer.parseInt(arg);
            if (weight <= 0) {
                throw new ValidationException("Вес человека должен быть больше нуля.");
            }
        } catch (NumberFormatException e) {
            throw new ValidationException("Вес человека должен быть числом типа int.");
        }
    }

    public static void validatePassportID (String arg) throws ValidationException {
        if (arg.length() > 28) {
            throw new ValidationException("Длина паспортых данных не может быть больше 28.");
        }
    }
}
