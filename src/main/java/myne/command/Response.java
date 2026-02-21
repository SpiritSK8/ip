package myne.command;

import myne.FerMyneFace;

/**
 * A class to encapsulate the response from executing commands.
 */
public class Response {
    private final String message;
    private final Status status;
    private final FerMyneFace face;

    /**
     * Creates a new Response object.
     * @param message The output of executing the command.
     * @param status One of the <code>Status</code> results.
     * @param face The face used for the chat.
     */
    public Response(String message, Status status, FerMyneFace face) {
        this.message = message;
        this.status = status;
        this.face = face;
    }

    public String getMessage() {
        return this.message;
    }

    public Status getStatus() {
        return status;
    }

    public FerMyneFace getFace() {
        return face;
    }
}

