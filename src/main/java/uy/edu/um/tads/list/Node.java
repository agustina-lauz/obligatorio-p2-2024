package uy.edu.um.tads.list;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Node<T>
{

    private T value;
    private Node<T> next;

    public Node(T value)
    {
        this.value = value;
    }

}
