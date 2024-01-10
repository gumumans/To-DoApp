package exceptions;

public class StatusException extends Exception {
    public StatusException(String message) {
        super(message);
    }

    public StatusException() {
        super("Ошибка смены статуса задачи!");
    }
}
