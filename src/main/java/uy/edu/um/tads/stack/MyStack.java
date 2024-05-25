package uy.edu.um.tads.stack;

public interface MyStack <T> {

    public void push(T value);
    public T pop() throws EmptyStackException;
    public T top() throws EmptyStackException;
    public boolean isEmpty();
    public void clear();
    public int size();

}
