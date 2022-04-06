package ru.os.ognisibiri.exceptions;

public class InvalidCommandNameException extends IllegalArgumentException {
    public InvalidCommandNameException() {
    }

    public InvalidCommandNameException(String s) {
        super(s);
    }
}
