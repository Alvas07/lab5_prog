package utils;

import data.TicketType;
import exceptions.ValidationException;
import utils.generators.IdGenerator;

import java.time.DateTimeException;
import java.time.LocalDate;

public class Validator {
    public static int validateId(String arg) throws ValidationException {
        try {
            int id = Integer.parseInt(arg);
            if (!IdGenerator.idIsUnique(id)) {
                throw new ValidationException("Билет с таким ID уже существует.");
            }
            return id;
        } catch (NumberFormatException e) {
            throw new ValidationException("ID должен быть числом типа int.");
        }
    }

    public static String validateName(String arg) throws ValidationException {
        if (arg == null || arg.trim().isEmpty()) {
            throw new ValidationException("Название билета не может быть пустым.");
        }
        return arg;
    }

    public static float validateCoordinatesX(String arg) throws ValidationException {
        try {
            float x = Float.parseFloat(arg);
            return x;
        } catch (NumberFormatException e) {
            throw new ValidationException("Координата X должна быть числом типа float.");
        }
    }

    public static Long validateCoordinatesY(String arg) throws ValidationException {
        try {
            Long y = Long.valueOf(arg);
            if (y > 332) {
                throw new ValidationException("Координата Y не может быть больше 332 или null.");
            }
            return y;
        } catch (NumberFormatException e) {
            throw new ValidationException("Координата Y должна быть числом типа long.");
        }
    }

    public static LocalDate validateCreationDate(String arg) throws ValidationException {
        try {
            LocalDate creationDate = LocalDate.parse(arg);
            return creationDate;
        } catch (DateTimeException e) {
            throw new ValidationException("Неверный формат даты.");
        }
    }

    public static float validatePrice(String arg) throws ValidationException {
        try {
            float price = Float.parseFloat(arg);
            if (price <= 0) {
                throw new ValidationException("Стоимость должна быть больше нуля.");
            }
            return price;
        } catch (NumberFormatException e) {
            throw new ValidationException("Стоимость билета должна быть числом типа float.");
        }
    }

    public static TicketType validateType(String arg) throws ValidationException {
        try {
            return TicketType.valueOf(arg);
        } catch (IllegalArgumentException e) {
            throw new ValidationException("Неверный параметр. Выберите один из предложенных.");
        }

    }

    public static Long validateLocationX(String arg) throws ValidationException {
        try {
            Long x = Long.valueOf(arg);
            return x;
        } catch (NumberFormatException e) {
            throw new ValidationException("Местоположение по X должно быть числом типа long.");
        }
    }

    public static Long validateLocationY(String arg) throws ValidationException {
        try {
            Long y = Long.valueOf(arg);
            return y;
        } catch (NumberFormatException e) {
            throw new ValidationException("Местоположение по Y должно быть числом типа long.");
        }
    }

    public static Integer validateLocationZ(String arg) throws ValidationException {
        try {
            Integer z = Integer.valueOf(arg);
            return z;
        } catch (NumberFormatException e) {
            throw new ValidationException("Местоположение по Z должно быть числом типа long.");
        }
    }

    public static Float validateHeight(String arg) throws ValidationException {
        try {
            Float height = Float.valueOf(arg);
            if (height <= 0) {
                throw new ValidationException("Рост человека должен быть больше нуля.");
            }
            return height;
        } catch (NumberFormatException e) {
            throw new ValidationException("Рост человека должен быть числом типа float.");
        }
    }

    public static int validateWeight(String arg) throws ValidationException {
        try {
            int weight = Integer.parseInt(arg);
            if (weight <= 0) {
                throw new ValidationException("Вес человека должен быть больше нуля.");
            }
            return weight;
        } catch (NumberFormatException e) {
            throw new ValidationException("Вес человека должен быть числом типа int.");
        }
    }

    public static String validatePassportID (String arg) throws ValidationException {
        if (arg.length() > 28) {
            throw new ValidationException("Длина паспортых данных не может быть больше 28.");
        }
        return arg;
    }
}
