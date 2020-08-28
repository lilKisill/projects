package myLinkedList;

public class Link<E> {
    private E data;
    private Link next;
    private Link previous;

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public Link getNext() {
        return next;
    }

    public void setNext(Link next) {
        this.next = next;
    }

    public Link getPrevious() {
        return previous;
    }

    public void setPrevious(Link previous) {
        this.previous = previous;
    }
}
