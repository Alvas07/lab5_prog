package utils.generators;

import data.Location;
import exceptions.ValidationException;
import utils.Validator;

import java.util.Scanner;

public class LocationGenerator {
    public static Location createLocation() {
        System.out.println("Добро пожаловать в Формирователь Местоположения.");

        Scanner scanner = new Scanner(System.in);
        String input;
        Location location = new Location();

        while (true) {
            try {
                System.out.print("Введите местоположение по X (Long): ");
                input = scanner.nextLine().trim();
                location.setX(Validator.validateLocationX(input));
                break;
            } catch (ValidationException e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.print("Введите местоположение по Y (Long): ");
                input = scanner.nextLine().trim();
                location.setY(Validator.validateLocationY(input));
                break;
            } catch (ValidationException e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.print("Введите местоположение по Z (Integer): ");
                input = scanner.nextLine().trim();
                location.setZ(Validator.validateLocationZ(input));
                break;
            } catch (ValidationException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("Местоположение создано.");
        return location;
    }
}
