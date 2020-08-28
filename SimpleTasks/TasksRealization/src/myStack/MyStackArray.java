package myStack;

public class MyStackArray<E> {
    private int size;
    private Object[] elements;
    private int top;

    public MyStackArray(int size) {
        this.size = size;
        this.elements = new Object[size];
        this.top = -1;
    }

    public boolean addElementToStack(E element) {
        if(!isFull()){
            elements[++top] = element;
            return true;
        } else {
            return false;
        }
    }

    public E deleteElementFromStack() {
        if(top != -1) {
            return (E) elements[top--];
        } else {
            return null;
        }
    }

    public E readTop() {
        if(!isEmpty()){
            return (E)elements[top];
        }
        System.out.println("The stack is empty");
       return null;
    }

    public boolean isEmpty() {
        return (top == -1);
    }

    public boolean isFull() {
        return (top == size - 1);
    }

    public int size() {
        return size;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
        top = -1;
    }
}
