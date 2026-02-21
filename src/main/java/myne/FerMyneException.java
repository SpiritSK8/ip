package myne;

public class FerMyneException extends RuntimeException {
    private final FerMyneFace face;

    public FerMyneException(String message, FerMyneFace face) {
        super(message);
        this.face = face;
    }

    public FerMyneFace getFace() {
        return face;
    }
}
