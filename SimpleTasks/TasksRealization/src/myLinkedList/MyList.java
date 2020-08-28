package myLinkedList;

public interface MyList<E> {
    boolean add(E element);
    void add(int index, E element);
    void addFirst(E element);
    void addLast(E element);
    void clear();
    boolean contains(Object o);
    E get(int index);
    E getFirst();
    E getLast();
    int indexOf(Object o);
    int lastIndexOf(Object o);
    boolean remove(int index);
    boolean removeFirst();
    boolean removeLast();
    boolean delete(Object o);
    E set(int index, E element);
    int size();
    boolean isEmpty();
    void writeList();
    Object[] toArray();
}
