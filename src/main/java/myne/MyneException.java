package myne;

public class MyneException extends RuntimeException {
    private final MyneFace face;
    private final User user;

    public MyneException(String message, MyneFace face, User user) {
        super(message);
        this.face = face;
        this.user = user;
    }

    public MyneFace getFace() {
        return face;
    }

    public User getUser() {
        return user;
    }
}
