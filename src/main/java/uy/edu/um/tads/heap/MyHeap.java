package uy.edu.um.tads.heap;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Comparator;

public class MyHeap<T> {
    private PriorityQueue<T> heap;

    public MyHeap(Comparator<T> comparator) {
        heap = new PriorityQueue<>(comparator);
    }

    public void insert(T item) {
        heap.add(item);
        if (heap.size() > 10) {
            heap.poll();
        }
    }

    public List<T> extractAllSorted() {
        List<T> sorted = new ArrayList<>();
        while (!heap.isEmpty()) {
            sorted.add(heap.poll());
        }
        return sorted;
    }
}


