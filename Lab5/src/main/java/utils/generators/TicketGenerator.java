package utils.generators;

import data.*;
import exceptions.ValidationException;
import utils.Validator;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TicketGenerator {
    public static Ticket createTicket() {
        System.out.println("Добро пожаловать в Формирователь Билета.");

        Scanner scanner = new Scanner(System.in);
        String input;
        boolean personFlag;
        Ticket ticket = new Ticket();

        while (true) {
            try {
                System.out.print("Введите название билета (String): ");
                input = scanner.nextLine().trim();
                ticket.setName(Validator.validateName(input));
                break;
            } catch (ValidationException e) {
                System.out.println(e.getMessage());
            }
        }

        ticket.setCoordinates(CoordinatesGenerator.createCoordinates());

        while (true) {
            try {
                System.out.print("Введите цену (float, >0): ");
                input = scanner.nextLine().trim();
                ticket.setPrice(Validator.validatePrice(input));
                break;
            } catch (ValidationException e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            try {
                TicketType[] values = TicketType.values();
                List<String> names = Arrays.stream(values).map(Enum::name).toList();
                System.out.print("Выберите тип билета (" + String.join(", ", names) + "): ");
                input = scanner.nextLine().trim().toUpperCase();
                ticket.setType(Validator.validateType(input));
                break;
            } catch (ValidationException e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.print("Хотите ли вы добавить пассажира? (да/нет): ");
                input = scanner.nextLine().trim().toLowerCase();
                personFlag = switch (input) {
                    case "да" -> true;
                    case "нет" -> false;
                    default -> throw new Exception("Необходимо выбрать один из двух вариантов ответа.");
                };
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        if (personFlag) {
            ticket.setPerson(PersonGenerator.createPerson());
        }

        System.out.println("Билет создан.");
        return ticket;
    }
}
