package info3.parcial2.Structures;

public class RedBlackTree<T extends Comparable<T>>
                            implements Tree<T>  {

    private RBNode<T> root;

    @Override
    public Tree<T> insert(T data) {
        RBNode<T> node = new RBNode<>(data);
        root = insert(root, node);
        recolorAndRotate(node);
        return this;
    }

    private RBNode<T> insert (RBNode<T> node, RBNode<T> nodeToInsert) {
        if(node == null)
            return nodeToInsert;
        if(nodeToInsert.getData().compareTo(node.getData()) < 0) {
            node.setLeftChild(insert(node.getLeftChild(), nodeToInsert));
            node.getLeftChild().setParent(node);
        } else if(nodeToInsert.getData().compareTo(node.getData()) > 0) {
            node.setRightChild(insert(node.getRightChild(), nodeToInsert));
            node.getRightChild().setParent(node);
        }
        return node;
    }

    private void recolorAndRotate (RBNode<T> node) {
        RBNode<T> parent = node.getParent();
        if(node != root && parent.isRed()) {
            RBNode<T> grandParent = node.getParent().getParent();
            RBNode<T> uncle = parent.isLeftChild() ? grandParent.getRightChild() : grandParent.getLeftChild();

            if(uncle != null && uncle.isRed())
                handleRecoloring(parent, uncle, grandParent);
            else if (parent.isLeftChild())
                handleLeftSituations(node, parent, grandParent);
            else if(!parent.isLeftChild())
                handleRightSituations(node, parent, grandParent);
        }
        if(root.isRed())
            root.flipColor();
    }

    private void handleRecoloring(RBNode<T> parent, RBNode<T> uncle, RBNode<T> grandParent) {
        uncle.flipColor();
        parent.flipColor();
        grandParent.flipColor();
        recolorAndRotate(grandParent);
    }

    private void handleLeftSituations(RBNode<T> node, RBNode<T> parent, RBNode<T> grandParent) {
        if (!node.isLeftChild())
            rotateLeft(parent);
        parent.flipColor();
        grandParent.flipColor();
        rotateRight(grandParent);
        recolorAndRotate(node.isLeftChild() ? parent : grandParent);
    }

    private void handleRightSituations (RBNode<T> node, RBNode<T> parent, RBNode<T> grandParent) {
        if(node.isLeftChild())
            rotateRight(parent);

        parent.flipColor();
        grandParent.flipColor();
        rotateLeft(grandParent);
        recolorAndRotate(node.isLeftChild() ? grandParent : parent);
    }

    private void rotateRight (RBNode<T> node) {
        RBNode<T> leftNode = node.getLeftChild();
        node.setLeftChild(leftNode.getRightChild());
        if(node.getLeftChild() != null)
            node.getLeftChild().setParent(node);
        leftNode.setRightChild(node);
        leftNode.setParent(node.getParent());
        updateChildrenOfParentNode(node, leftNode);
        node.setParent(leftNode);
    }

    private void updateChildrenOfParentNode(RBNode<T> node, RBNode<T> tempNode) {
        if(node.getParent() == null)
            root = tempNode;
        else if (node.isLeftChild())
            node.getParent().setLeftChild(tempNode);
        else
            node.getParent().setRightChild(tempNode);
    }

    private void rotateLeft(RBNode<T> node) {
        RBNode<T> rightNode = node.getRightChild();
        node.setRightChild(rightNode.getLeftChild());
        if(node.getRightChild() != null)
            node.getRightChild().setParent(node);
        rightNode.setLeftChild(node);
        rightNode.setParent(node.getParent());
        updateChildrenOfParentNode(node, rightNode);
        node.setParent(rightNode);
    }
    @Override
    public void delete(T data) throws Exception {
        throw new Exception("Not allowed operation");
    }

    @Override
    public void traverse() {
        traverseInOrder(root);
    }
    private void traverseInOrder(RBNode<T> node) {
        if(node != null) {
            traverseInOrder((node.getLeftChild()));
            System.out.println(node);
            traverseInOrder(node.getRightChild());
        }
    }

    @Override
    public T getMax() {
        if(isEmpty())
            return null;
        return getMax(root);
    }

    private T getMax(RBNode<T> node) {
        if(node.getRightChild() != null)
            return getMax(node.getRightChild());
        return node.getData();
    }

    @Override
    public T getMin() {
        if (isEmpty())
            return null;
        return getMin(root);
    }

    private T getMin(RBNode<T> node) {
        if(node.getLeftChild() != null)
            return getMin(node.getLeftChild());
        return node.getData();
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }
}
