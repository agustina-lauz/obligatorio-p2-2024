package uy.edu.um.tads.stack;

import uy.edu.um.tads.list.MyLinkedListImpl;

public class MyStackImpl<T> extends MyLinkedListImpl<T> implements MyStack <T>{

    @Override
    public void push(T value) {
        super.addToBeginning(value);
    }

    @Override
    public T pop() throws EmptyStackException {
        if (isEmpty())
            throw new EmptyStackException();
        T value = super.get(0);
        super.remove(value);
        return value;
    }

    @Override
    public T top() throws EmptyStackException {
        if (isEmpty())
            throw  new EmptyStackException();
        return super.get(0);
    }

    @Override
    public void clear(){
        super.clear();
    }

    @Override
    public boolean isEmpty() {
        return super.isEmpty();
    }

    @Override
    public int size() {
        return super.size();
    }

}
