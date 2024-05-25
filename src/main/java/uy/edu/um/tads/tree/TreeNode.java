package uy.edu.um.tads.tree;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uy.edu.um.tads.list.MyList;

import java.util.Comparator;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TreeNode<V> {
    private V value;
    private TreeNode<V> left;
    private TreeNode<V> right;

    public TreeNode(V value) {
        this.value = value;
    }

    public int depth() {
        if (left == null && right == null)
            return 0;

        int depthLeft = 0, depthRight = 0;
        if (left != null)
            depthLeft = left.depth();
        if (right != null)
            depthRight = right.depth();
        return Math.max(depthLeft, depthRight) + 1;
    }

    public int breadthSize() {
        if (left == null && right == null)
            return 1;
        int counter = 0;
        if (left != null)
            counter += left.breadthSize();
        if (right != null)
            counter += right.breadthSize();
        return counter;
    }

    public int size() {
        int size = 1;
        size += left == null ? 0 : left.size();
        size += right == null ? 0 : right.size();
        return size;
    }

    public void preorder(MyList<V> laLista) {
        laLista.add(this.value);
        if (left != null){
            left.preorder(laLista);
        }
        if (right != null){
            right.preorder(laLista);
        }
    }

    public void postorder(MyList<V> laLista) {
        if (left != null){
            left.postorder(laLista);
        }
        if (right != null){
            right.postorder(laLista);
        }
        laLista.add(this.value);
    }

    public void inorder(MyList<V> laLista) {
        if (left != null){
            left.inorder(laLista);
        }
        laLista.add(this.value);
        if (right != null){
            right.inorder(laLista);
        }
    }

    public void breadth(MyList<V> laLista) {
        if (this.left == null && this.right == null){
            laLista.add(this.value);
            return;
        }
        if (left != null){
            left.breadth(laLista);
        }
        if (right != null){
            right.breadth(laLista);
        }
    }

    public boolean insert(V value, Comparator<V> comparator) {

        int comp = comparator.compare(value, this.value);
        if (comp == 0) {
            return false;
        }
        if (comp > 0) {
            if (this.right == null)
                this.right = new TreeNode<>(value);
            else
                this.right.insert(value, comparator);
        }
        if (comp < 0) {
            if (this.left == null)
                this.left = new TreeNode<>(value);
            else
                this.left.insert(value, comparator);
        }
        return true;
    }

    public void printInOrder() {
        if(left != null)
            left.printInOrder();
        System.out.print(this.value.toString() + ",");
        if(right != null)
            right.printInOrder();
    }

    public void printPostOrder() {
        if(left != null)
            left.printPostOrder();
        if(right != null)
            right.printPostOrder();
        System.out.print(this.value.toString() + ",");
    }

    public void printPreOrden() {
        System.out.print(this.value.toString() + ",");
        if(left != null)
            left.printPreOrden();
        if(right != null)
            right.printPreOrden();
    }

    public V search(Comparable key, Comparator<V> comparator) {
        int comp = comparator.compare((V) key, this.value);
        if (comp == 0) {
            return this.value;
        }
        if (comp < 0) {
            if (this.left == null){
                return null;
            }
            return this.left.search(key, comparator);
        }
        if (comp > 0) {
            if (this.right == null){
                return null;
            }
            return this.right.search(key, comparator);
        }
        return null;
    }
}
