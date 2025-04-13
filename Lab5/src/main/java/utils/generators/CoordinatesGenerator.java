package utils.generators;

import data.Coordinates;
import exceptions.ValidationException;
import utils.Validator;

import java.util.Scanner;

public class CoordinatesGenerator {
    public static Coordinates createCoordinates() {
        System.out.println("Добро пожаловать в Формирователь Координат.");

        Scanner scanner = new Scanner(System.in);
        String input;
        Coordinates coordinates = new Coordinates();

        while (true) {
            try {
                System.out.print("Введите координату X (float): ");
                input = scanner.nextLine().trim();
                coordinates.setX(Validator.validateCoordinatesX(input));
                break;
            } catch (ValidationException e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.print("Введите координату Y (Long, <=332): ");
                input = scanner.nextLine().trim();
                coordinates.setY(Validator.validateCoordinatesY(input));
                break;
            } catch (ValidationException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("Координаты созданы.");
        return coordinates;
    }
}
