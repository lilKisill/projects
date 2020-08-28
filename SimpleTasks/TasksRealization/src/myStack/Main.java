package myStack;

public class Main {
    public static void main(String[] args) {
        MyStackArray<Integer> stack = new MyStackArray<>(5);
        stack.addElementToStack(1);
        stack.addElementToStack(2);
        stack.addElementToStack(3);
        stack.addElementToStack(4);
        stack.addElementToStack(5);
        System.out.println(stack.readTop());
        stack.deleteElementFromStack();
        stack.addElementToStack(6);
        System.out.println(stack.readTop());
    }
}
