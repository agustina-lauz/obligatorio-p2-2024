package uy.edu.um.tads.heap;

public class MyHeap<T extends Comparable<T>> {

    private T[] heapArray;
    private int size;
    private int capacity;

    public MyHeap(int capacity ) {
        this.capacity = capacity;
        this.size = 0;
        this.heapArray = (T[]) new Comparable[ capacity ];
    }

    public void insert( T dato ) {
        if ( size >= capacity ) {
            System.out.println(" heap lleno ");
            return;
        }
        heapArray[size] = dato;
        heapUp(size);
        size ++;
    }

    public T get(){
        if (size == 0) {
            System.out.println(" El heap esta Vacio ");
            return null;
        }
        T  top = heapArray[0];
        //swap(0, size);
        heapArray[0] = heapArray[size - 1];
        size --;
        heapDown(0);
        return  top;
    }


    private void heapUp(int index ) {
        int padreIndex = (index - 1) / 2;
        while (index > 0 && heapArray[index].compareTo(heapArray[padreIndex]) > 0) {
            swap(index,padreIndex);
            index = padreIndex;
            padreIndex = (index - 1) / 2;
        }
    }

    private int heapDown(int index){
        int leftChildrenIndex, rightChildrenIndex, largerChildrenIndex;

        while (index < size){
            leftChildrenIndex = index * 2 + 1;
            rightChildrenIndex = index * 2 + 2;
            largerChildrenIndex = index;

            if(leftChildrenIndex < size && heapArray[leftChildrenIndex].compareTo(heapArray[largerChildrenIndex]) > 0) {
                largerChildrenIndex = leftChildrenIndex;
            }
            if (rightChildrenIndex < size && heapArray[rightChildrenIndex].compareTo(heapArray[largerChildrenIndex]) > 0) {
                largerChildrenIndex = rightChildrenIndex;
            }
            if(index == largerChildrenIndex){
                break;
            }
            swap(index,largerChildrenIndex);
            index  = largerChildrenIndex;
        }

        return index;
    }

    private void swap(int i, int j ) {
        T temp = heapArray[i];
        heapArray[i] = heapArray[j];
        heapArray[j] = temp;
    }

}

