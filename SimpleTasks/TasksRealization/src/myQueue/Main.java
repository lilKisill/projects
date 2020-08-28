package myQueue;

public class Main {
    public static void main(String[] args) {
        PriorityQueue queue = new PriorityQueue(10);
        queue.insert(10);
        queue.insert(5);
        queue.insert(1);
        queue.insert(7);
        queue.insert(7);
        queue.insert(30);
        queue.insert(5);
        queue.insert(5);
        queue.insert(5);
        queue.insert(5);
        queue.remove();
        queue.insert(100);
        System.out.println(queue.peekMax());
        System.out.println(queue.peekMin());
        System.out.println(queue.isFull());
        System.out.println(queue.isEmpty());
        System.out.println(queue.size());
    }
}
