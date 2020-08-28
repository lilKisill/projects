package Array;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Задача:
 * 1. Заполнение массив случайными числами, вывыод массива, вывод максимального, минимального и среднего значений.
 * 2. Алгоритм сортировки пузырьком по возрастанию и по спаданию
 * 3. Удаление элемента массива если это возможно
 */
public class Main {
    List<Integer> list = new ArrayList<>();
    public static final int TOP_BORDER = 1;
    public static final int BOTTOM_BORDER = 100;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the value of the array: ");
        int capacityOfArray = scanner.nextInt();
        int[] array = new int[capacityOfArray];
        fillRandomNumbers(array);
        writeArrayToConsole(array);
        bubbleSort(array, true);
        writeArrayToConsole(array);
        foundSpecialNumbers(array);
        array = deleteNumber(array, 3);
        writeArrayToConsole(array);
    }

    public static void fillRandomNumbers(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = TOP_BORDER + (int) (Math.random() * BOTTOM_BORDER);
        }
    }

    public static void writeArrayToConsole(int[] array) {
        System.out.print("Your array: ");
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    public static void foundSpecialNumbers(int[] array) {
        int min = array[0], max = array[0];
        int sum = 0;
        for (int value : array) {
            sum += value;
            if (max < value) {
                max = value;
            }
            if (min > value) {
                min = value;
            }
        }
        float avg = (float) sum / array.length;
        System.out.println("Max = " + max);
        System.out.println("Min = " + min);
        System.out.println("Average = " + avg);
    }

    public static void bubbleSort(int[] array, boolean choice) {
        if (choice) {
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array.length - i - 1; j++) {
                    if (array[j] > array[j + 1]) {
                        int space = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = space;
                    }
                }
            }
        } else {
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array.length - i - 1; j++) {
                    if (array[j] < array[j + 1]) {
                        int space = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = space;
                    }
                }
            }
        }
    }

    public static int[] deleteNumber(int[] array, int id) {
        if (id < array.length) {
            int[] cloneArray = new int[array.length - 1];
            for (int i = 0; i < id; i++) {
                cloneArray[i] = array[i];
            }

            for (int i = id + 1; i < array.length; i++) {
                cloneArray[i - 1] = array[i];
            }
            return cloneArray;
        } else {
            System.out.println("We can't do it.");
            return array;
        }
    }
}


/*private static final int DEFAULT_CAPACITY = 10;
    private int[] elements;
    private int size;

    public MyArrayList() {
        this.elements = new int[DEFAULT_CAPACITY];
    }

    public MyArrayList(int initialCapacity) {
        if (initialCapacity >= 0) {
            this.elements = new int[initialCapacity];
        } else {
            throw new IllegalStateException("Capacity can't be less than 0!");
        }
    }

    public boolean add(int element) {
        if (size == elements.length) {
            elements = increaseArray();
        }
        elements[size] = element;
        size++;
        return true;
    }

    public void add(int index, int element) {
        isIndexExist(index);
        int[] temp = elements;
        if (size == elements.length) {
            elements = increaseArray();
        } else {
            elements = new int[temp.length + 1];
        }
        System.arraycopy(temp, 0, elements, 0, index);
        elements[index] = element;
        System.arraycopy(temp, index, elements, index + 1, temp.length - index);
        size++;
    }

    public int get(int index) {
        isIndexExist(index);
        return elements[index];
    }

    public int set(int value, int index) {
        isIndexExist(index);
        int previous = elements[index];
        elements[index] = value;
        return previous;
    }

    public int remove(int index) {
        isIndexExist(index);
        int[] temp = elements;
        elements = new int[temp.length - 1];
        int value = temp[index];
        System.arraycopy(temp, 0, elements, 0, index);
        System.arraycopy(temp, index + 1, elements, index, temp.length - index - 1);
        size--;
        return value;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        *//*for (int i = 0; i < elements.length; i++) {
            elements[i] = 0;
        }*//*
        size = 0;
    }

    public void writeList() {
        for (int i = 0; i < size; i++) {
            System.out.print(elements[i] + " ");
        }
        System.out.println();
    }

    public boolean contains(int value) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == value) {
                return true;
            }
        }
        return false;
    }

    public int indexOf(int value) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == value) {
                return i;
            }
        }
        return -1;
    }

    public int lastIndexOf(int value) {
        for (int i = size - 1; i >= 0; i--) {
            if (elements[i] == value) {
                return i;
            }
        }
        return -1;
    }

    public void trimToSize(){
        int[] temp = elements;
        elements = new int[size];
        System.arraycopy(temp, 0, elements, 0, size);
    }

    public void sort(boolean choice) {
        if (choice) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size - i - 1; j++) {
                    if (elements[j] > elements[j + 1]) {
                        int space = elements[j];
                        elements[j] = elements[j + 1];
                        elements[j + 1] = space;
                    }
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size - i - 1; j++) {
                    if (elements[j] < elements[j + 1]) {
                        int space = elements[j];
                        elements[j] = elements[j + 1];
                        elements[j + 1] = space;
                    }
                }
            }
        }
    }

    private int[] increaseArray() {
        int[] bigArray = new int[(elements.length * 2)];
        System.arraycopy(elements, 0, bigArray, 0, elements.length);
        return bigArray;
    }

    private int isIndexExist(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Element can't be found! "
                    + "Number of elements in array = " + size
                    + ". Total size of array = " + elements.length);
        }
        return index;
    }*/