package uy.edu.um.tads.list;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MyLinkedListImpl<T> implements MyList<T>
{

    private Node<T> first;
    private Node<T> last;

    @Override
    public void add(T value)
    {
        if (value != null)
        {
            Node<T> elementToAdd = new Node<>(value);
            if (this.first == null)
            { // si la lista es vacia
                this.first = elementToAdd;
                this.last = elementToAdd;
            }
            else
            { // en caso de no ser vacia se agrega al final
                this.last.setNext(elementToAdd);
                this.last = elementToAdd;
            }
        }
        else
        {
            // si el elemento es vacio se ignora
        }
    }

    @Override
    public void addToBeginning(T value)
    {
        if (value != null)
        {
            Node<T> elementToAdd = new Node<>(value);
            if (this.first == null)
            { // si la lista es vacia
                this.first = elementToAdd;
                this.last = elementToAdd;
            }
            else
            { // en caso de no ser vacia se agrega al comienzo
                elementToAdd.setNext(this.first);
                this.first = elementToAdd;
            }

        }
        else
        {
            // si el elemento es vacio se ignora
        }
    }

    @Override
    public T get(int position) throws IndexOutOfBoundsException
    {
        if (position < 0 || position >= size())
        {
            throw new IndexOutOfBoundsException("ERROR: Position out of bounds.");
        }

        int tempPosition = 0;
        Node<T> temp = this.first;
        // Se busca el nodo que corresponde con la posicion
        while (temp != null && tempPosition != position)
        {
            temp = temp.getNext();
            tempPosition++;
        }
        // si se encontró la posición se retorna el valor
        // en caso que se haya llegado al final y no se llegó a la posición se retorna null
        return tempPosition == position ? temp.getValue() : null;
    }

    @Override
    public boolean contains(T value)
    {
        boolean contains = false;
        Node<T> temp = this.first;

        while (temp != null && !temp.getValue().equals(value))
        {
            temp = temp.getNext();
        }
        // Si no se llego al final de la lista, se encontro el valor
        return temp != null;
    }

    @Override
    public void remove(T value)
    {
        Node<T> beforeSearchValue = null;
        Node<T> searchValue = this.first;

        // Busco el elemento a eliminar teniendo en cuenta mantener una referencia al elemento anterior
        while (searchValue != null && !searchValue.getValue().equals(value))
        {
            beforeSearchValue = searchValue;
            searchValue = searchValue.getNext();
        }

        if (searchValue != null)
        { // si encontre el elemento a eliminar
            // Verifico si es el primer valor (caso borde) y no es el ultimo
            if (searchValue == this.first && searchValue != this.last)
            {
                Node<T> temp = this.first;
                this.first = this.first.getNext(); // salteo el primero
                temp.setNext(null); // quito referencia del elemento eliminado al siguiente.
                // Verifico si es el primer valor (caso borde) y no el primero
            }
            else if (searchValue == this.last && searchValue != this.first)
            {
                beforeSearchValue.setNext(null);
                this.last = beforeSearchValue;
                // Si es el primer valor y el ultimo (lista de un solo valor)
            }
            else if (searchValue == this.last && searchValue == this.first)
            {
                this.first = null;
                this.last = null;
            }
            else
            { // resto de los casos
                beforeSearchValue.setNext(searchValue.getNext());
                searchValue.setNext(null);
            }
        }
        else
        {
            // Si no es encuentra el valor a eliminar no se realiza nada
        }
    }

    @Override
    public int size()
    {
        int size = 0;
        Node<T> temp = this.first;
        while (temp != null)
        {
            temp = temp.getNext();
            size++;
        }
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public void clear(){
        this.first = null;
        this.last = null;
    }
}
