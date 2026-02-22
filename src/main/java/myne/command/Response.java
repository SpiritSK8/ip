package myne.command;

import myne.MyneFace;
import myne.User;

/**
 * A class to encapsulate the response from executing commands.
 */
public class Response {
    private final String message;
    private final Status status;
    private final MyneFace face;
    private final User user;

    /**
     * Creates a new Response object.
     *
     * @param message The output of executing the command.
     * @param status  One of the <code>Status</code> results.
     * @param face    The face used for the chat.
     * @param user    The user sending this response.
     */
    public Response(String message, Status status, MyneFace face, User user) {
        this.message = message;
        this.status = status;
        this.face = face;
        this.user = user;
    }

    public String getMessage() {
        return this.message;
    }

    public Status getStatus() {
        return status;
    }

    public MyneFace getFace() {
        return face;
    }

    public User getUser() {
        return user;
    }
}

