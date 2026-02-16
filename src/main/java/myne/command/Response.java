package myne.command;

/**
 * A class to encapsulate the response from executing commands.
 */
public class Response {
    private final String message;
    private final Status status;

    /**
     * Creates a new Response object.
     * @param message The output of executing the command.
     * @param status One of the <code>Status</code> results.
     */
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

