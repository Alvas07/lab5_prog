package utils.generators;

import data.Person;
import exceptions.ValidationException;
import utils.Validator;

import java.util.Scanner;

public class PersonGenerator {
    public static Person createPerson() {
        System.out.println("Добро пожаловать в Формирователь Пассажира.");

        Scanner scanner = new Scanner(System.in);
        String input;
        boolean locationFlag;
        Person person = new Person();

        while (true) {
            try {
                System.out.print("Введите рост пассажира (Float, >0): ");
                input = scanner.nextLine().trim();
                person.setHeight(Validator.validateHeight(input));
                break;
            } catch (ValidationException e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.print("Введите вес пассажира (int, >0): ");
                input = scanner.nextLine().trim();
                person.setWeight(Validator.validateWeight(input));
                break;
            } catch (ValidationException e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.print("Введите паспортный номер (String, maxLength=28). Нажмите Enter, если не хотите добавлять данный параметр: ");
                input = scanner.nextLine().trim();
                if (!input.isEmpty()) {
                    person.setPassportID(Validator.validatePassportID(input));
                }
                break;
            } catch (ValidationException e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.print("Хотите ли вы добавить местоположение пассажира? (да/нет): ");
                input = scanner.nextLine().trim().toLowerCase();
                locationFlag = switch (input) {
                    case "да" -> true;
                    case "нет" -> false;
                    default -> throw new IllegalArgumentException("Необходимо выбрать один из двух вариантов ответа.");
                };
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        if (locationFlag) {
            person.setLocation(LocationGenerator.createLocation());
        }

        System.out.println("Пассажир создан.");
        return person;
    }
}
