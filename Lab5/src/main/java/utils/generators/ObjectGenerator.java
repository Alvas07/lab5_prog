package utils.generators;

import exceptions.ObjectCreationException;
import managers.ScannerManager;

import java.util.Scanner;
import java.util.function.Predicate;

public abstract class ObjectGenerator<T> {
    private final Scanner scanner = ScannerManager.getScanner();

    public abstract T create(boolean fileMode) throws ObjectCreationException;

    public String askString(String prompt, Predicate<String> validator, boolean fileMode) throws ObjectCreationException {
        if (fileMode) {
            prompt = null;
        }
        while (true) {
            System.out.println(prompt);
            String input = scanner.nextLine().trim();
            if (validator.test(input)) {
                return input;
            } else if (input.isEmpty() && validator.test("")) {
                return null;
            }
            if (fileMode) {
                throw new ObjectCreationException("Неверный формат ввода.");
            } else {
                System.out.println("Неверный формат ввода.");
            }
        }
    }

    public Enum askEnum(String prompt, Enum[] exceptedValues, Predicate<String> validator, boolean fileMode) throws ObjectCreationException {
        if (fileMode) {
            prompt = null;
        } else {
            System.out.println("Доступные значения:");
            for (Enum value : exceptedValues) {
                System.out.println(">>> " + value.toString());
            }
        }
        while (true) {
            System.out.println(prompt);
            String input = scanner.nextLine().trim();
            if (validator.test(input)) {
                for (Enum value : exceptedValues) {
                    if (value.toString().equals(input.toUpperCase())) {
                        return value;
                    }
                }
                if (fileMode) {
                    throw new ObjectCreationException("Значение не найдено.");
                } else {
                   System.out.println("Значение не найдено.");
                }
            }
            if (fileMode) {
                throw new ObjectCreationException("Неверный формат ввода.");
            } else {
                System.out.println("Неверный формат ввода.");
            }
        }
    }

    public Integer askInteger(String prompt, Predicate<Integer> validator, boolean fileMode) throws ObjectCreationException {
        if (fileMode) {
            prompt = null;
        }
        while (true) {
            System.out.println(prompt);
            String input = scanner.nextLine().trim();
            try {
                Integer number = Integer.parseInt(input);
                if (validator.test(number)) {
                    return number;
                } else if (fileMode) {
                    throw new ObjectCreationException("Значение не прошло валидацию.");
                } else {
                    System.out.println("Значение не прошло валидацию.");
                }
            } catch (NumberFormatException e) {
                if (input.isEmpty() && validator.test(null)) {
                    return null;
                }
                if (fileMode) {
                    throw new ObjectCreationException("Неверный формат ввода.");
                } else {
                    System.out.println("Неверный формат ввода.");
                }
            }
        }
    }

    public Long askLong(String prompt, Predicate<Long> validator, boolean fileMode) throws ObjectCreationException {
        if (fileMode) {
            prompt = null;
        }
        while (true) {
            System.out.println(prompt);
            String input = scanner.nextLine().trim();
            try {
                Long number = Long.parseLong(input);
                if (validator.test(number)) {
                    return number;
                } else if (fileMode) {
                    throw new ObjectCreationException("Значение не прошло валидацию.");
                } else {
                    System.out.println("Значение не прошло валидацию.");
                }
            } catch (NumberFormatException e) {
                if (input.isEmpty() && validator.test(null)) {
                    return null;
                }
                if (fileMode) {
                    throw new ObjectCreationException("Неверный формат ввода.");
                } else {
                    System.out.println("Неверный формат ввода.");
                }
            }
        }
    }

    public Double askDouble(String prompt, Predicate<Double> validator, boolean fileMode) throws ObjectCreationException {
        if (fileMode) {
            prompt = null;
        }
        while (true) {
            System.out.println(prompt);
            String input = scanner.nextLine().trim();
            try {
                Double number = Double.parseDouble(input);
                if (validator.test(number)) {
                    return number;
                } else if (fileMode) {
                    throw new ObjectCreationException("Значение не прошло валидацию.");
                } else {
                    System.out.println("Значение не прошло валидацию.");
                }
            } catch (NumberFormatException e) {
                if (input.isEmpty() && validator.test(null)) {
                    return null;
                }
                if (fileMode) {
                    throw new ObjectCreationException("Неверный формат ввода.");
                } else {
                    System.out.println("Неверный формат ввода.");
                }
            }
        }
    }

    public Float askFloat(String prompt, Predicate<Float> validator, boolean fileMode) throws ObjectCreationException {
        if (fileMode) {
            prompt = null;
        }
        while (true) {
            System.out.println(prompt);
            String input = scanner.nextLine().trim();
            try {
                Float number = Float.parseFloat(input);
                if (validator.test(number)) {
                    return number;
                } else if (fileMode) {
                    throw new ObjectCreationException("Значение не прошло валидацию.");
                } else {
                    System.out.println("Значение не прошло валидацию.");
                }
            } catch (NumberFormatException e) {
                if (input.isEmpty() && validator.test(null)) {
                    return null;
                }
                if (fileMode) {
                    throw new ObjectCreationException("Неверный формат ввода.");
                } else {
                    System.out.println("Неверный формат ввода.");
                }
            }
        }
    }
}
