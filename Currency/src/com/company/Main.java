package com.company;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int scanFormode;
         do {
             System.out.println("Выберете язык. Введите 1 - для английского, 2 - для украинского, 0 - для выхода.");
             scanFormode = scan.nextInt();
            switch (scanFormode) {
                case 1:
                    System.out.print("Введите число: ");
                    double englishNumber = scan.nextDouble();
                    EnglishCurrency Ecurrency = new EnglishCurrency(englishNumber);
                    String englishResult = Ecurrency.convertToWords(Ecurrency.getWholePartOfAmount(), Ecurrency.getFractionalPartOfAmount());
                    System.out.println(englishResult);
                    break;
                case 2:
                    System.out.print("Введите число: ");
                    double ukrainianNumber = scan.nextDouble();
                    UkrainianCurrency Ucurrency = new UkrainianCurrency(ukrainianNumber);
                    String ukrainianResult = Ucurrency.convertToWords(Ucurrency.getWholePartOfAmount(), Ucurrency.getFractionalPartOfAmount());
                    System.out.println(ukrainianResult);
                    break;
                case 0:
                    break;
            }
        } while (scanFormode!=0);
    }
}
