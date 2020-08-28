package myQueue;

public class MyQueueArray<E> {
    private Object[] elements;
    private int size;
    private int head;
    private int tail;

    public MyQueueArray(int initialCapacity) {
        this.elements = new Object[initialCapacity];
        this.size = 0;
        this.tail = -1;
        this.head = 0;
    }

    public boolean insert(E element) {
        if (!isFull()) {
            elements[++tail] = element;
            size++;
            return true;
        } else {
            return false;
        }
    }

    public E remove() {
        if (head != -1) {
            size--;
            return (E) elements[head++];
        } else {
            return null;
        }
    }

    public E getFront() {
        if (!isEmpty()) {
            return (E) elements[head];
        }
        System.out.println("The queue is empty");
        return null;
    }

    public E getRear() {
        if (!isEmpty()) {
            return (E) elements[tail];
        }
        System.out.println("The queue is empty");
        return null;
    }

    public boolean isFull() {
        return (size == elements.length);
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
        tail = -1;
        head = 0;
    }
}
