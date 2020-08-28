package myQueue;

public class PriorityQueue {
    private int size;
    private int[] elements;

    public PriorityQueue(int initialCapacity) {
        this.size = 0;
        this.elements = new int[initialCapacity];
    }

    public boolean insert(int element) {
        if (isFull()) {
            System.out.println("Queue is full");
            return false;
        } else if (isEmpty()) {
            elements[size++] = element;
            return true;
        } else {
            for (int i = 0; i < size; i++) {
                if (element < elements[i]) {
                    int[] temp = elements;
                    elements = new int[temp.length];
                    System.arraycopy(temp, 0, elements, 0, i);
                    elements[i] = element;
                    System.arraycopy(temp, i, elements, i + 1, temp.length - i - 1);
                    size++;
                    return true;
                }
            }
            elements[size++] = element;
            return true;
        }
    }

    public int remove() {
        if (!isEmpty()) {
            return elements[--size];
        }
        System.out.println("The queue is empty");
        return -1;
    }

    public int peekMin() {
        if (!isEmpty()) {
            return elements[0];
        }
        System.out.println("The queue is empty");
        return -1;
    }

    public int peekMax() {
        if (!isEmpty()) {
            return elements[size - 1];
        }
        System.out.println("The queue is empty");
        return -1;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public boolean isFull() {
        return (size == elements.length);
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = 0;
        }
        size = 0;
    }
}
