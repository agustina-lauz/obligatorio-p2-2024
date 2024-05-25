package uy.edu.um.tads.stack;

public class EmptyStackException extends Exception {
    public EmptyStackException() {
        super("Stack is empty");
    }
}
