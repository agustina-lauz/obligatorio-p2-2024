package uy.edu.um.tads.list;


public interface MyList<T>
{

    public void add(T value);

    public void addToBeginning(T value);

    public T get(int position) throws IndexOutOfBoundsException;

    public boolean contains(T value);

    public void remove(T value);

    public int size();

    public Node<T> getFirst();

    public boolean isEmpty();

    public void clear();
}
