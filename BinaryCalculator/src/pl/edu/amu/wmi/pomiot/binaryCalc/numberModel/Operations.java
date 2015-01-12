package pl.edu.amu.wmi.pomiot.binaryCalc.numberModel;

import pl.edu.amu.wmi.pomiot.binaryCalc.converter.Converter;

/**
 * Created by Tomasz on 2015-01-08.
 */
public class Operations {

    public static double addition(String firstNumber, String secondNumber) {

        /**
         *
         * [0] znak
         * [1] wykładnik
         * [2] mantysa
         *
         **/

        /*
        System.out.println();
        System.out.println("-----------\n Adding numbers\n-----------");
        System.out.println("Fist number in addition is: " + firstNumber);
        System.out.println("Second number in addition is: " + secondNumber);
        */

        String[] firstNumberParts = firstNumber.split(" ");
        String[] secondNumberParts = secondNumber.split(" ");

        int firstExponent = Converter.posBinaryToInt(firstNumberParts[1]);
        int secondExponent = Converter.posBinaryToInt(secondNumberParts[1]);

        StringBuilder zeroesFromExponentDifference = new StringBuilder("");

        String firstSignificandForCalculation = firstNumberParts[0];
        String secondSignificandForCalculation = secondNumberParts[0];

        if(firstNumberParts[0].equals("0")){
            firstSignificandForCalculation += "0";
        }
        else {
            firstSignificandForCalculation += "0";
        }

        if(secondNumberParts[0].equals("0")){
            secondSignificandForCalculation += "0";
        }
        else {
            secondSignificandForCalculation += "0";
        }

        int exponentDifference = firstExponent - secondExponent;
        if (exponentDifference > 0) {
            //pierwszy jest większy

            for (int i = exponentDifference; i > 0; i--) {
                zeroesFromExponentDifference.append("0");
            }
            secondSignificandForCalculation += zeroesFromExponentDifference.toString();
            firstSignificandForCalculation += "1" + firstNumberParts[2];
            secondSignificandForCalculation += "1" + secondNumberParts[2];
            firstSignificandForCalculation += zeroesFromExponentDifference.toString();
        }
        else if (exponentDifference < 0){
            //drugi jest większy
            for (int i = exponentDifference; i < 0; i++) {
                zeroesFromExponentDifference.append("0");

            }
            firstSignificandForCalculation += zeroesFromExponentDifference.toString();
            firstSignificandForCalculation += "1" + firstNumberParts[2];
            secondSignificandForCalculation += "1" + secondNumberParts[2];
            secondSignificandForCalculation += zeroesFromExponentDifference.toString();
        }
        else{
            firstSignificandForCalculation += "1" + firstNumberParts[2];
            secondSignificandForCalculation += "1" + secondNumberParts[2];
        }

        if (firstNumberParts[0].equals("1")) {
            firstSignificandForCalculation = convertToNegative(firstSignificandForCalculation);
        }
        if (secondNumberParts[0].equals("1")) {
            secondSignificandForCalculation = convertToNegative(secondSignificandForCalculation);
        }

        System.out.println("First element after preparation to addition:  " + firstSignificandForCalculation);
        System.out.println("Second element after preparation to addition: " + secondSignificandForCalculation);

        String result = add(firstSignificandForCalculation, secondSignificandForCalculation);

        //reformatting after addition

        int biggerExponent = Math.max(firstExponent, secondExponent);

        if(result.charAt(0)=='1')
        result = convertToNegative(result);

        double supportingConversion = supportingConversion(result, biggerExponent);

        return supportingConversion;
    }

    private static String add(String firstNumber, String secondNumber){

        char[] firstCalculationArray = firstNumber.substring(0,13).toCharArray();
        char[] secondCalculationArray = secondNumber.substring(0,13).toCharArray();
        StringBuilder resultCalculationArray = new StringBuilder("");

        int carryBit = 0;

        int bitAtNumberOne =0;
        int bitAtNumberTwo =0;

        int lengthForLoop = Math.max(firstCalculationArray.length,secondCalculationArray.length)-2;

        for(int i = lengthForLoop; i>=0; i--){

            if(firstNumber.charAt(i)=='1'){bitAtNumberOne=1;}
            else{bitAtNumberOne=0;}

            if(secondNumber.charAt(i)=='1'){bitAtNumberTwo=1;}
            else{bitAtNumberTwo=0;}

            int current = 0;
            current = bitAtNumberOne+bitAtNumberTwo+carryBit;

            switch(current) {
                case 0:
                    resultCalculationArray.append("0");
                    carryBit=0;
                    break;

                case 1:
                    resultCalculationArray.append("1");
                    carryBit=0;
                    break;

                case 2:
                    resultCalculationArray.append("0");
                    carryBit=1;
                    break;

                case 3:
                    resultCalculationArray.append("1");
                    carryBit=1;
                    break;

                default:
                    resultCalculationArray.append("-WRONG-");
                    break;

            }
        }

        //System.out.println("\nThe result of addition is: ");
        resultCalculationArray.reverse();
       // System.out.println(resultCalculationArray);

        return resultCalculationArray.toString();
    }

    public static double substraction(String firstNumber, String secondNumber){

        /**
         *
         * [0] znak
         * [1] wykładnik
         * [2] mantysa
         *
         **/

        //System.out.println();
        //System.out.println("-----------\n Substracting numbers\n-----------");
        //System.out.println("Fist number in substraction is: " + firstNumber);

        String[] firstNumberParts = firstNumber.split(" ");
        String[] secondNumberParts = secondNumber.split(" ");

        int firstExponent = Converter.posBinaryToInt(firstNumberParts[1]);
        int secondExponent = Converter.posBinaryToInt(secondNumberParts[1]);

        StringBuilder zeroesFromExponentDifference = new StringBuilder("");

        String firstSignificandForCalculation = firstNumberParts[0];
        String secondSignificandForCalculation = secondNumberParts[0];

        if(firstNumberParts[0].equals("0")){
            firstSignificandForCalculation += "0";
        }
        else {
            firstSignificandForCalculation += "0";
        }

        if(secondNumberParts[0].equals("0")){
            secondSignificandForCalculation += "0";
        }
        else {
            secondSignificandForCalculation += "0";
        }

        int exponentDifference = firstExponent - secondExponent;
        if (exponentDifference > 0) {

            //pierwszy jest większy

            for (int i = exponentDifference; i > 0; i--) {
                zeroesFromExponentDifference.append("0");
            }
            secondSignificandForCalculation += zeroesFromExponentDifference.toString();
            firstSignificandForCalculation += "1" + firstNumberParts[2];
            secondSignificandForCalculation += "1" + secondNumberParts[2];
            firstSignificandForCalculation += zeroesFromExponentDifference.toString();
        }
        else if (exponentDifference < 0){

            //drugi jest większy

            for (int i = exponentDifference; i < 0; i++) {
                zeroesFromExponentDifference.append("0");
            }
            firstSignificandForCalculation += zeroesFromExponentDifference.toString();
            firstSignificandForCalculation += "1" + firstNumberParts[2];
            secondSignificandForCalculation += "1" + secondNumberParts[2];
            secondSignificandForCalculation += zeroesFromExponentDifference.toString();
        }
        else{
            firstSignificandForCalculation += "1" + firstNumberParts[2];
            secondSignificandForCalculation += "1" + secondNumberParts[2];
        }
        if (firstNumberParts[0].equals("1")) {
            firstSignificandForCalculation = convertToNegative(firstSignificandForCalculation);
        }
        if (secondNumberParts[0].equals("1")) {
            secondSignificandForCalculation = convertToNegative(secondSignificandForCalculation);
        }

        System.out.println("First element after preparation to substraction:  " + firstSignificandForCalculation);
        System.out.println("Second element after preparation to substraction: " + secondSignificandForCalculation);

        String result = substract(firstSignificandForCalculation, secondSignificandForCalculation);

        //reformatting after substraction

        if(result.charAt(0)=='1') {
            result = convertToNegative(result);
            System.out.println("After negation: " + result);
        }

        int biggerExponent = Math.max(firstExponent, secondExponent);

        double supportingConversion = supportingConversion(result, biggerExponent);

        return supportingConversion;
    }

    public static String substract(String firstNumber, String secondNumber){

        char[] firstCalculationArray = firstNumber.substring(0,13).toCharArray();
        char[] secondCalculationArray = secondNumber.substring(0,13).toCharArray();
        StringBuilder resultCalculationArray = new StringBuilder("");

        int carryBit = 0;

        int bitAtNumberOne =0;
        int bitAtNumberTwo =0;

        int lengthForLoop = Math.max(firstCalculationArray.length,secondCalculationArray.length)-2;

        for(int i = lengthForLoop; i>=0; i--){

            if(firstNumber.charAt(i)=='1'){bitAtNumberOne=1;}
            else{bitAtNumberOne=0;}

            if(secondNumber.charAt(i)=='1'){bitAtNumberTwo=1;}
            else{bitAtNumberTwo=0;}

            int current = 0;
            current = bitAtNumberOne-bitAtNumberTwo-carryBit;

            switch(current) {
                case 1:
                    resultCalculationArray.append("1");
                    carryBit=0;
                    break;

                case 0:
                    resultCalculationArray.append("0");
                    carryBit=0;
                    break;

                case -1:
                    resultCalculationArray.append("1");
                    carryBit=1;
                    break;

                case -2:
                    resultCalculationArray.append("0");
                    carryBit=1;
                    break;

                default:
                    resultCalculationArray.append("-WRONG-");
                    break;
            }
        }

        System.out.println("\nThe result of substraction is: ");
        resultCalculationArray.reverse();
        System.out.println(resultCalculationArray);

        return resultCalculationArray.toString();
    }

    private static Double supportingConversion(String resultCalculationArray, int exponent) {

        double sign = 1;
        if(resultCalculationArray.charAt(0)=='1'){
            sign=-1;
        }

        StringBuilder res = new StringBuilder(resultCalculationArray.substring(1));
        res.insert(exponent+2-15,".");

        String[] numberParts = res.toString().split("\\.");

        double result =(Converter.binaryToInt(numberParts[0])+Converter.binaryToFraction(numberParts[1]))*sign;

        return result;
    }

    public static double multiplication(String firstNumber, String secondNumber){

        /**
         *
         * [0] znak
         * [1] wykładnik
         * [2] mantysa
         *
         **/

        //System.out.println();
        //System.out.println("-----------\n Substracting numbers\n-----------");
        //System.out.println("Fist number in substraction is: " + firstNumber);

        String[] firstNumberParts = firstNumber.split(" ");
        String[] secondNumberParts = secondNumber.split(" ");

        int firstExponent = Converter.posBinaryToInt(firstNumberParts[1]);
        int secondExponent = Converter.posBinaryToInt(secondNumberParts[1]);

        StringBuilder zeroesFromExponentDifference = new StringBuilder("");

        String firstSignificandForCalculation = firstNumberParts[0];
        String secondSignificandForCalculation = secondNumberParts[0];

        if(firstNumberParts[0].equals("0")){
            firstSignificandForCalculation += "0";
        }
        else {
            firstSignificandForCalculation += "0";
        }

        if(secondNumberParts[0].equals("0")){
            secondSignificandForCalculation += "0";
        }
        else {
            secondSignificandForCalculation += "0";
        }

        int exponentDifference = firstExponent - secondExponent;
        if (exponentDifference > 0) {

            //pierwszy jest większy

            for (int i = exponentDifference; i > 0; i--) {
                zeroesFromExponentDifference.append("0");
            }
            secondSignificandForCalculation += zeroesFromExponentDifference.toString();
            firstSignificandForCalculation += "1" + firstNumberParts[2];
            secondSignificandForCalculation += "1" + secondNumberParts[2];
            firstSignificandForCalculation += zeroesFromExponentDifference.toString();
        }
        else if (exponentDifference < 0){

            //drugi jest większy

            for (int i = exponentDifference; i < 0; i++) {
                zeroesFromExponentDifference.append("0");
            }
            firstSignificandForCalculation += zeroesFromExponentDifference.toString();
            firstSignificandForCalculation += "1" + firstNumberParts[2];
            secondSignificandForCalculation += "1" + secondNumberParts[2];
            secondSignificandForCalculation += zeroesFromExponentDifference.toString();
        }
        else{
            firstSignificandForCalculation += "1" + firstNumberParts[2];
            secondSignificandForCalculation += "1" + secondNumberParts[2];
        }
        if (firstNumberParts[0].equals("1")) {
            firstSignificandForCalculation = convertToNegative(firstSignificandForCalculation);
        }
        if (secondNumberParts[0].equals("1")) {
            secondSignificandForCalculation = convertToNegative(secondSignificandForCalculation);
        }

        System.out.println("First element after preparation to multiplication:  " + firstSignificandForCalculation);
        System.out.println("Second element after preparation to multiplication: " + secondSignificandForCalculation);

        String result = substract(firstSignificandForCalculation, secondSignificandForCalculation);

        //reformatting after multiplication

        if(result.charAt(0)=='1') {
            result = convertToNegative(result);
            System.out.println("After multiplication: " + result);
        }

        int biggerExponent = Math.max(firstExponent, secondExponent);

        double supportingConversion = supportingConversion(result, biggerExponent);

        return supportingConversion;
    }

    public static String convertToNegative(String significand){

        StringBuilder result = new StringBuilder("1");

        int lastIndexOfOne = significand.lastIndexOf("1");

              for (int i = 1; i < lastIndexOfOne; i++) {

            if(significand.charAt(i) == '1'){
                result.append("0");
            }
            else result.append("1");
        }

        result.append("100000000000");

        //result.insert(0,"1");

        return result.toString();
    }

}
