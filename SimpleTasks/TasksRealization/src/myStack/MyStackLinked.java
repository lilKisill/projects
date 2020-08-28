package myStack;

public class MyStackLinked<E> {
    private int size;
    private Node top;

    public boolean addElementToStack(E node) {
        if (node == null || !(node instanceof Node))
            return false;
        else {
            Node newNode = (Node) node;
            newNode.setNext(top);
            top = newNode;
            size++;
            return true;
        }
    }

    public E deleteElementFromStack() {
        if (top != null) {
            Node proxyTop = top;
            top = top.getNext();
            size--;
            return (E) proxyTop.getData();
        } else {
            return null;
        }
    }

    public E readTop() {
        if (top != null) {
            return (E) top.getData();
        } else {
            return null;
        }
    }

    public void clear() {
        top = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
