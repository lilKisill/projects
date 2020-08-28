package myArrayList;

public class MyArrayList<E> implements MyList<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public MyArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(int initialCapacity) {
        if (initialCapacity >= 0) {
            this.elements = new Object[initialCapacity];
        } else {
            throw new IllegalStateException("Capacity can't be less than 0!");
        }
    }

    @Override
    public boolean add(E element) {
        if (size == elements.length) {
            elements = increaseArray();
        }
        elements[size] = element;
        size++;
        return true;
    }

    @Override
    public void add(int index, E element) {
        if (isIndexExist(index)) {
            Object[] temp = elements;
            if (size == elements.length) {
                elements = increaseArray();
            } else {
                elements = new Object[temp.length + 1];
            }
            System.arraycopy(temp, 0, elements, 0, index);
            elements[index] = element;
            System.arraycopy(temp, index, elements, index + 1, temp.length - index);
            size++;
        }
    }

    @Override
    public E get(int index) {
        if (isIndexExist(index)) {
            return (E) this.elements[index];
        } else {
            throw new IndexOutOfBoundsException("Element can't be found! "
                    + "Number of elements in array = " + size
                    + ". Total size of array = " + elements.length);
        }
    }

    @Override
    public E set(int index, E element) {
        if (isIndexExist(index)) {
            E oldValue = (E) this.elements[index];
            elements[index] = element;
            return oldValue;
        } else {
            throw new IndexOutOfBoundsException("Element can't be found! "
                    + "Number of elements in array = " + size
                    + ". Total size of array = " + elements.length);
        }
    }

    @Override
    public E remove(int index) {
        if (isIndexExist(index)) {
            Object[] temp = elements;
            elements = new Object[temp.length - 1];
            E value = (E) temp[index];
            System.arraycopy(temp, 0, elements, 0, index);
            System.arraycopy(temp, index + 1, elements, index, temp.length - index - 1);
            size--;
            return value;
        } else {
            throw new IndexOutOfBoundsException("Element can't be found! "
                    + "Number of elements in array = " + size
                    + ". Total size of array = " + elements.length);
        }
    }

    @Override
    public boolean delete(Object o) {
        if(contains(o)){
            remove(indexOf(o));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        System.arraycopy(elements, 0, array, 0, size);
        return array;
    }

    @Override
    public boolean contains(Object o) {
        return this.indexOf(o) >= 0;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == o) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (elements[i] == o) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void trimToSize() {
        Object[] temp = elements;
        elements = new Object[size];
        System.arraycopy(temp, 0, elements, 0, size);
    }

    @Override
    public void writeList() {
        for (int i = 0; i < size; i++) {
            System.out.print(elements[i] + " ");
        }
        System.out.println();
    }

    private Object[] increaseArray() {
        Object[] bigArray = new Object[(elements.length * 2)];
        System.arraycopy(elements, 0, bigArray, 0, elements.length);
        return bigArray;
    }

    private boolean isIndexExist(int index) {
        return index < size && index >= 0;
    }

}
