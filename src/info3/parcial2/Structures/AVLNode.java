package info3.parcial2.Structures;

public class AVLNode<T extends Comparable<T>> {
    private T data;
    private int height = 1;
    private AVLNode<T> leftChild;
    private AVLNode<T> rightChild;

    public AVLNode(T data) {
        this.data = data;
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

    public AVLNode<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(AVLNode<T> leftChild) {
        this.leftChild = leftChild;
    }

    public AVLNode<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(AVLNode<T> rightChild) {
        this.rightChild = rightChild;
    }
}
