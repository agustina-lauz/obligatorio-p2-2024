package uy.edu.um.tads.tree;

import java.util.Comparator;


public class BinarySearchTreeImpl<V> extends BinaryTreeImpl<V> implements BinarySearchTree<V>
{
    private Comparator<V> comparator;

    public BinarySearchTreeImpl(Comparator<V> comparator)
    {
        this.comparator = comparator;
    }

    @Override
    public boolean insert(V value) {
        if (root == null){
            root = new TreeNode<>(value);
            return true;
        }
        return root.insert(value,comparator);
    }

    @Override
    public V remove(Comparable key) {
        return null;
    }

    @Override
    public V search(Comparable key) {
        if (root == null){
            return null;
        }
        return root.search(key,comparator);
    }


}