package exceptions;

public class CommandExecuteException extends Exception {
    public CommandExecuteException(String message) {
        super("Ошибка при исполнении команды: " + message);
    }
}
