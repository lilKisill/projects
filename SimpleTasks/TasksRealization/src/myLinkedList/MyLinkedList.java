package myLinkedList;

public class MyLinkedList<E> implements MyList<E> {
    private int size;
    private Node head;
    private Node tail;


    @Override
    public boolean add(E element) {
        addLast(element);
        return true;
    }

    @Override
    public void add(int index, E element) {
        if (isIndexExist(index)) {
            Node current = head;
            Node note = new Node();
            int count = 0;
            if (index == 0) {
                addFirst(element);
            } else {
                while (current != null) {
                    if (count == index - 1) {
                        note.setNext(current.getNext());
                        note.setData(element);
                        current.setNext(note);
                        size++;
                        break;
                    }
                    count++;
                    current = current.getNext();
                }
            }
        } else {
            throw new IndexOutOfBoundsException("Element can't be found! "
                    + "Number of elements in array = " + size);
        }
    }

    @Override
    public void addFirst(E element) {
        Node node = new Node();
        node.setData(element);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            node.setNext(head);
            head = node;
        }
        size++;
    }

    @Override
    public void addLast(E element) {
        Node note = new Node();
        note.setData(element);
        if (tail == null) {
            head = note;
            tail = note;
        } else {
            tail.setNext(note);
            tail = note;
        }
        size++;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public boolean contains(Object o) {
        return this.indexOf(o) >= 0;
    }

    @Override
    public E get(int index) {
        if (isIndexExist(index)) {
            Node current = head;
            int count = 0;
            while (current != null) {
                if (count == index) {
                    break;
                }
                count++;
                current = current.getNext();
            }
            return (E) current.getData();
        } else {
            throw new IndexOutOfBoundsException("Element can't be found! "
                    + "Number of elements in array = " + size);
        }
    }

    @Override
    public E getFirst() {
        if (head != null) {
            return (E) head.getData();
        } else {
            throw new IndexOutOfBoundsException("Element can't be found! "
                    + "Number of elements in array = " + size);
        }
    }

    @Override
    public E getLast() {
        if (tail != null) {
            return (E) tail.getData();
        } else {
            throw new IndexOutOfBoundsException("Element can't be found! "
                    + "Number of elements in array = " + size);
        }
    }

    @Override
    public int indexOf(Object o) {
        Node current = head;
        int count = 0;
        while (current != null) {
            if (current.getData() == o) {
                return count;
            }
            count++;
            current = current.getNext();
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        Node current = head;
        int count = 0;
        int lastCount = -1;
        while (current != null) {
            if (current.getData() == o) {
                lastCount = count;
            }
            count++;
            current = current.getNext();
        }
        return lastCount;
    }

    @Override
    public boolean remove(int index) {
        if (isIndexExist(index)) {
            if (head == null) {
                return false;
            }

            if (head == tail) {
                head = null;
                tail = null;
                return true;
            }

            if (index == 0) {
                Node deleteNote = head;
                head = head.getNext();
                size--;
                return true;
            }

            Node note = head;
            int count = 0;
            while (note.getNext() != null) {
                if (count == index - 1) {
                    if (tail == note.getNext()) {
                        tail = note;
                    }
                    note.setNext(note.getNext().getNext());
                    size--;
                    return true;
                }
                count++;
                note = note.getNext();
            }
            return false;
        } else {
            throw new IndexOutOfBoundsException("Element can't be found! "
                    + "Number of elements in array = " + size);
        }
    }

    @Override
    public boolean removeFirst() {
        return remove(0);
    }

    @Override
    public boolean removeLast() {
        return remove(size - 1);
    }

    @Override
    public boolean delete(Object o) {
        if (head == null) {
            return false;
        }

        if (head == tail) {
            head = null;
            tail = null;
            return true;
        }

        if (head.getData() == o) {
            head = head.getNext();
            size--;
            return true;
        }

        Node note = head;
        while (note.getNext() != null) {
            if (note.getNext().getData() == o) {
                if (tail == note.getNext()) {
                    tail = note;
                }
                note.setNext(note.getNext().getNext());
                size--;
                return true;
            }
            note = note.getNext();
        }
        return false;
    }

    @Override
    public E set(int index, E element) {
        if (isIndexExist(index)) {
            Node current = head;
            int count = 0;
            E oldValue = null;
            while (current != null) {
                if (count == index) {
                    oldValue = (E) current.getData();
                    current.setData(element);
                    break;
                }
                count++;
                current = current.getNext();
            }
            return oldValue;
        } else {
            throw new IndexOutOfBoundsException("Element can't be found! "
                    + "Number of elements in array = " + size);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void writeList() {
        Node current = head;
        while (current != null) {
            System.out.print(current.getData() + " ");
            current = current.getNext();
        }
        System.out.println();
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        Node current = head;
        int i = 0;
        while (current != null) {
            array[i] = current.getData();
            current = current.getNext();
            i++;
        }
        return array;
    }

    private boolean isIndexExist(int index) {
        return index < size && index >= 0;
    }

}
