package myne;

public class MyneException extends RuntimeException {
    private final MyneFace face;
    private final String name;

    public MyneException(String message, MyneFace face, String name) {
        super(message);
        this.face = face;
        this.name = name;
    }

    public MyneFace getFace() {
        return face;
    }

    public String getName() {
        return name;
    }
}
