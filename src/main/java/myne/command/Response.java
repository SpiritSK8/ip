package myne.command;

/**
 * A class to encapsulate the response from executing commands.
 */
public class Response {
    private final String message;
    private Status status;

    public Response(String message, Status status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return this.message;
    }

    public Status getStatus() {
        return status;
    }
}

