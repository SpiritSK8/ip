package myne;

public class FerMyneException extends RuntimeException {
    private final FerMyneFace face;
    private final String name;

    public FerMyneException(String message, FerMyneFace face, String name) {
        super(message);
        this.face = face;
        this.name = name;
    }

    public FerMyneFace getFace() {
        return face;
    }

    public String getName() {
        return name;
    }
}
