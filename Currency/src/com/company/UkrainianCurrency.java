package com.company;

import java.util.ArrayList;
import java.util.Collections;

public class UkrainianCurrency{
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

    public UkrainianCurrency(double amount){
        this.wholePartOfAmount = (int) amount;
        this.fractionalPartOfAmount = (int)Math.round(((amount - wholePartOfAmount) * 100));
    }

    public String convertToWords(int wholePart, int FractionPart){
        String convertResultOfAmount = "";
        convertResultOfAmount += convertPartsToWords(wholePart,1);
        convertResultOfAmount += convertPartsToWords(FractionPart,0);
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
                        convertPart += getTextForNumbers(parameterForAmount, numbersArray.get(i)) + "";
                        parameterForAmount--;
                    }
                    else{
                        checkForZero = 0;
                        parameterForAmount--;
                    }
                }
            }
            return convertPart;
        }
    }



    public static String getTextForNumbers(int position, int numberPosition){
        String[] billion = {"мільярд ", "мільярди ", "мільярдів "};
        String[] million = {"мільйон ", "мільйони ", "мільйонів "};
        String[] hundred = {"тисяча ", "тисячи ", "тисяч "};
        String[] hryvnias = {"гривня ", "гривні ", "гривень "};
        String[] kopecks = {"копійка ", "копійки ", "копійок "};
        switch (position) {
            case 0:
                return getResultText(kopecks, numberPosition);
            case 1:
                return getResultText(hryvnias, numberPosition);
            case 2:
                return getResultText(hundred, numberPosition);
            case 3:
                return getResultText(million, numberPosition);
            case 4:
                return getResultText(billion, numberPosition);
            default:
                return null;
        }
    }

    public static String getResultText( String[] textArrayOfCases, int numberPosition){
        if(firstReminder){
            firstReminder = false;
            return textArrayOfCases[2];
        }
        else if(numberPosition == 1){
            return textArrayOfCases[0];
        }
        else if ((numberPosition == 2 | numberPosition == 3 | numberPosition == 4) & firstReminder == false) {
            firstReminder = false;
            return textArrayOfCases[1];
        }
        else {
            return textArrayOfCases[2];
        }
    }


    public static String getNumbers(int numberPosition, int sizeOfNumber){
        String[] firstUnit = {"","один ","два ","три ","чотири ","п'ять ","шість ","сім ","вісім ","дев'ять "};
        String[] secondUnit = {"","одна ","дві ","три ","чотири ","п'ять ","шість ","сім ","вісім ","дев'ять "};
        String[] unitsOfTen = {"десять ","одинадцять ","дванадцять ","тринадцять ","чотирнадцять ", "п'ятнадцять ","шістнадцять ","сімнадцять ","вісімнадцять ","дев'ятнадцять "};
        String[] decades = {"","десять ","двадцять ","тридцять ","сорок ","п'ятдесят ","шістдесят ", "сімдесят ","вісімдесят ","дев'яносто "};
        String[] hundreds= {"","сто ","двісті ","триста ","чотириста ","п'ятсот ","шістсот ","сімсот ", "вісімсот ","дев'ятсот "};
        switch (sizeOfNumber%3) {
            case 1:
                if(secondReminder){
                    secondReminder = false;
                    firstReminder = true;
                    return unitsOfTen[numberPosition];
                }
                else if (sizeOfNumber==7 || sizeOfNumber==10){
                    return firstUnit[numberPosition];
                }
                else {
                    return secondUnit[numberPosition];
                }
            case 2:
                if(numberPosition < 2 & numberPosition > 0){
                    secondReminder = true;
                    return "";
                }
                else {
                    return decades[numberPosition];
                }
            case 0:
                return hundreds[numberPosition];
            default:
                return null;
        }
    }
}
