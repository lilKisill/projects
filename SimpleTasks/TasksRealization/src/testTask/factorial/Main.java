package testTask.factorial;

public class Main {
    public static void main(String[] args) {
        SumFactorialsDigitsCalculator calculator = new SumFactorialsDigitsCalculator();
        int sumAllDigits = calculator.getSum(100);
        System.out.println(sumAllDigits);
    }
}
