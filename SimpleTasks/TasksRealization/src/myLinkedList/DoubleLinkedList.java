package myLinkedList;

public class DoubleLinkedList<E> {
    private int size;
    private Link head;
    private Link tail;

    public void addFirst(E element) {
        Link node = new Link();
        node.setData(element);
        if (isEmpty()) {
            tail = node;
        } else {
            head.setPrevious(node);
        }
        node.setNext(head);
        head = node;
        size++;
    }

    public void addLast(E element) {
        Link node = new Link();
        node.setData(element);
        if (isEmpty()) {
            head = node;
        } else {
            tail.setNext(node);
            node.setPrevious(tail);
        }
        tail = node;
        size++;
    }

    public boolean deleteFirst() {
        if (!isEmpty()) {
            Link temp = head;
            if (head.getNext() == null) {
                tail = null;
            } else {
                head.getNext().setPrevious(null);
            }
            head = head.getNext();
            return true;
        } else {
            return false;
        }
    }

    public boolean deletelast() {
        if (!isEmpty()) {
            Link temp = tail;
            if (head.getNext() == null) {
                tail = null;
            } else {
                tail.getPrevious().setNext(null);
            }
            tail = tail.getPrevious();
            return true;
        } else {
            return false;
        }
    }


    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
}
