package info3.parcial2.Structures;

import info3.parcial2.TreePrinter;

public class Node<K extends Comparable<K>, T> implements TreePrinter.PrintableNode {
    private T data;
    private K key;
    private int height = 1;
    private Node<K, T> leftChild;
    private Node<K, T> rightChild;

    public Node(K key, T data) {
        this.data = data;
        this.key = key;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Node<K, T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node<K, T> leftChild) {
        this.leftChild = leftChild;
    }

    public Node<K, T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node<K, T> rightChild) {
        this.rightChild = rightChild;
    }

    /**
     * Get left child
     */
    @Override
    public TreePrinter.PrintableNode getLeft() {
        return getLeftChild();
    }

    /**
     * Get right child
     */
    @Override
    public TreePrinter.PrintableNode getRight() {
        return getRightChild();
    }

    /**
     * Get text to be printed
     */
    @Override
    public String getText() {
        return String.valueOf(this.key);
    }
}
