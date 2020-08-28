package myArrayList;

public interface MyList<E> {
    boolean add(E element);
    void add(int index, E element);
    E get(int index);
    E set(int index, E element);
    E remove(int index);
    boolean delete(Object o);
    Object[] toArray();
    boolean contains(Object o);
    int indexOf(Object o);
    int lastIndexOf(Object o);
    int size();
    void clear();
    boolean isEmpty();
    void trimToSize();
    void writeList();
}
