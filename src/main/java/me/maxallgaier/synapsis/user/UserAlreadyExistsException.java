package me.maxallgaier.synapsis.user;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException() {
        super("user already exists");
    }
}
