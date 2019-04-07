package by.epam.fourthtask.exception;

public class BuilderInitializationException extends Exception
{
    public BuilderInitializationException() {
    }

    public BuilderInitializationException(String message) {
        super(message);
    }

    public BuilderInitializationException(String message, Throwable cause) {
        super(message, cause);
    }

    public BuilderInitializationException(Throwable cause) {
        super(cause);
    }
}
