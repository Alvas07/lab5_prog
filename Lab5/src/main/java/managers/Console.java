package managers;

import exceptions.FileReadException;
import exceptions.UnknownCommandException;

import java.util.Scanner;

public class Console {
    public static String DATA_PATH;

    public void start(String[] args) {
        Scanner scanner = ScannerManager.getScanner();
        CollectionManager collectionManager = new CollectionManager();
        CommandManager commandManager = new CommandManager(collectionManager);
        try {
            System.out.println("Загрузка информации о коллекции из файла...");
            FileManager fileManager = new FileManager(args[0], collectionManager);
            fileManager.fillCollectionFromXml();
            DATA_PATH = args[0];
            System.out.println("Загрузка прошла успешно!");
        } catch (FileReadException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }

        System.out.println("Добро пожаловать в приложение для управления коллекцией билетов!");
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine().trim();
            if (!command.isEmpty()) {
                try {
                    commandManager.startExecuting(command);
                } catch (UnknownCommandException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
