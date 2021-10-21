package info3.parcial2.Structures;

public class TreeNode <T extends Comparable<T>>{
    private T data;
    private boolean isRed;
    private TreeNode<T> leftChild;
    private TreeNode<T> rightChild;
    private TreeNode<T> parent;

    public TreeNode() {
    }

    public TreeNode(T data, TreeNode<T> parent) {
        this.data = data;
        this.parent = parent;
    }

    public boolean isLeftChild() {
        return this == parent.getLeftChild();
    }

    public void flipColor() {
        setRed(!isRed());
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

    public TreeNode<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(TreeNode<T> leftChild) {
        this.leftChild = leftChild;
    }

    public TreeNode<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(TreeNode<T> rightChild) {
        this.rightChild = rightChild;
    }

    public TreeNode<T> getParent() {
        return parent;
    }

    public void setParent(TreeNode<T> parent) {
        this.parent = parent;
    }

}
