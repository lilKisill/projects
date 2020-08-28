package myQueue;

public class MyQueueLinked<E> {
    private int size;
    private Node head;
    private Node tail;

    public boolean push(E element) {
        if (element == null || !(element instanceof Node))
            return false;
        else {
            size++;
            Node node = (Node) element;
            if (head == null) {
                tail = node;
                head = tail;
                return true;
            } else {
                tail.setNext(node);
                tail = tail.getNext();
                return true;
            }
        }
    }

    public Node pop() {
        if (head == null) {
            return null;
        } else {
            size--;
            if (head == tail) {
                Node deleteNode = head;
                head = null;
                tail = null;
                return deleteNode;
            } else {
                Node deleteNode = head;
                head = head.getNext();
                return deleteNode;
            }
        }
    }

    public E getFront() {
        if (head != null) {
            return (E) head.getData();
        } else {
            return null;
        }
    }

    public E getRear() {
        if (tail != null) {
            return (E) tail.getData();
        } else {
            return null;
        }
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
