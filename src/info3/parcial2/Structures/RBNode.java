package info3.parcial2.Structures;

public class RBNode<T extends Comparable<T>>{
    private T data;
    private boolean isRed;
    private RBNode<T> leftChild;
    private RBNode<T> rightChild;
    private RBNode<T> parent;

    public RBNode() {
    }

    public RBNode(T data) {
        this.data = data;
    }

    public boolean isLeftChild() {
        return this == parent.getLeftChild();
    }

    public void flipColor() {
        isRed = !isRed;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isRed() {
        return isRed;
    }

    public void setRed(boolean red) {
        isRed = red;
    }

    public RBNode<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(RBNode<T> leftChild) {
        this.leftChild = leftChild;
    }

    public RBNode<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(RBNode<T> rightChild) {
        this.rightChild = rightChild;
    }

    public RBNode<T> getParent() {
        return parent;
    }

    public void setParent(RBNode<T> parent) {
        this.parent = parent;
    }

}
