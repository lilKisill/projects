package com.company;

import java.util.ArrayList;
import java.util.Collections;

public class EnglishCurrency {
    private int wholePartOfAmount;
    private int fractionalPartOfAmount;
    private static boolean firstReminder = false;
    private static boolean secondReminder = false;

    public int getWholePartOfAmount() {
        return wholePartOfAmount;
    }

    public int getFractionalPartOfAmount() {
        return fractionalPartOfAmount;
    }

    public EnglishCurrency(double amount){
        this.wholePartOfAmount = (int) amount;
        this.fractionalPartOfAmount = (int)Math.round(((amount - wholePartOfAmount) * 100));
    }

    public String convertToWords(int wholePart, int fractionPart){
        String convertResultOfAmount = "";
        convertResultOfAmount += convertPartsToWords(wholePart,1);
        if(wholePart!=0 & fractionPart!=0){
            convertResultOfAmount += "and ";
        }
        convertResultOfAmount += convertPartsToWords(fractionPart,0);
        return convertResultOfAmount;
    }

    public static String convertPartsToWords(int partOfAmount, int parameterForAmount){
        if(partOfAmount == 0){
            return "";
        }
        else {
            ArrayList<Integer> numbersArray = new ArrayList<>();
            String convertPart = "";
            int count = 0;
            while (partOfAmount != 0) {
                numbersArray.add(partOfAmount % 10);
                partOfAmount = partOfAmount / 10;
                count++;
                if (count > 3) {
                    count = 1;
                    parameterForAmount++;
                }
            }
            Collections.reverse(numbersArray);
            int numberOfDigits = numbersArray.size();
            int checkForZero = 0;
            for (int i = 0; i < numbersArray.size(); i++) {
                if(numbersArray.get(i) == 0){
                    checkForZero++;
                }
                convertPart += getNumbers(numbersArray.get(i), numberOfDigits) + "";
                numberOfDigits--;
                if (numberOfDigits % 3 == 0) {
                    if(checkForZero!= 3 | i == numbersArray.size() - 1){
                        checkForZero = 0;
                        convertPart += getTextForNumbers(parameterForAmount, numbersArray.size(), numbersArray.get(i)) + "";
                        parameterForAmount--;
                    }
                    else {
                        checkForZero = 0;
                        parameterForAmount--;
                    }
                }
            }
            return convertPart;
        }
    }



    public static String getTextForNumbers(int position, int numberPosition, int numberValue){
        String[] billion = {"billion "};
        String[] million = {"million "};
        String[] thousand = {"thousand "};
        String[] dollar = {"dollar ", "dollars "};
        String[] cent = {"cent ", "cents "};
        switch (position) {
            case 0:
                return getResultText(cent, numberPosition, numberValue);
            case 1:
                return getResultText(dollar, numberPosition, numberValue);
            case 2:
                return thousand[0];
            case 3:
                return million[0];
            case 4:
                return billion[0];
            default:
                return null;
        }
    }

    public static String getResultText( String[] textArrayOfCases, int numberPosition, int numberValue){
        if(numberPosition == 1 & numberValue == 1){
            return textArrayOfCases[0];
        }
        else {
            return textArrayOfCases[1];
        }
    }


    public static String getNumbers(int numberPosition, int sizeOfNumber){
        String[] firstUnit = {"","one ","two ","three ","four ","five ","six ","seven ","eight ","nine "};
        String[] unitsOfTen = {"ten ","eleven ","twelve ","thirteen ","fourteen ", "fifteen ","sixteen ","seventeen ","eighteen ","nineteen "};
        String[] decades = {"","ten ","twenty ","thirty ","fourty ","fifty ","sixty ", "seventy ","eighty ","ninety "};
        String[] hundreds= {"","one hundred ","two hundred ","three hundred ","four hundred ","five hundred ","six hundred ","seven hundred ", "eight hundred ","nine hundred "};
        switch (sizeOfNumber%3) {
            case 1:
                if(secondReminder){
                    secondReminder = false;
                    return unitsOfTen[numberPosition];
                }
                else if (firstReminder){
                    firstReminder=false;
                    return "- " + firstUnit[numberPosition];
                }
                else {
                    return firstUnit[numberPosition];
                }
            case 2:
                if(numberPosition < 2 & numberPosition > 0){
                    secondReminder = true;
                    return "";
                }
                else {
                    if(numberPosition!=0){
                        firstReminder = true;
                    }
                    return decades[numberPosition];
                }
            case 0:
                return hundreds[numberPosition];
            default:
                return null;
        }
    }
}
