package myLinkedList;

public class Main {
    public static void main(String[] args) {
        MyList<Integer> list = new MyLinkedList<Integer>();
        list.addFirst(1);
        list.addLast(10);
        list.add(11);
        list.add(2, 7);
        list.set(1, 100);
        list.removeFirst();
        list.removeLast();
        list.writeList();
        System.out.println(list.size());
        System.out.println(list.getLast() + " " + list.getFirst());
    }
}
