package testTask.scobka;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the value of the array: ");
        int n = scanner.nextInt();
        System.out.println(func(n));
    }

    static int func(int n) {
        if (n <= 0) {
            return 1;
        }
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += func(i - 1) * func(n - i);
        }
        return sum;
    }
}
