package pl.edu.amu.wmi.pomiot.binaryCalc.numberModel;

import pl.edu.amu.wmi.pomiot.binaryCalc.converter.Converter;

import java.util.ArrayList;
import java.util.List;

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

        //System.out.println("First element after preparation to addition:  " + firstSignificandForCalculation);
        //System.out.println("Second element after preparation to addition: " + secondSignificandForCalculation);

        String result = add(firstSignificandForCalculation, secondSignificandForCalculation);

        //reformatting after addition

        int biggerExponent = Math.max(firstExponent, secondExponent);

        if(result.charAt(0)=='1')
        result = convertToNegative(result);

        double supportingConversion = supportingConversion(result, biggerExponent);

        return supportingConversion;
    }

    private static String add(String firstNumber, String secondNumber){

        char[] firstCalculationArray = firstNumber.toCharArray();//substring(0,13);
        char[] secondCalculationArray = secondNumber.toCharArray();//substring(0,13);
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

    private static Double supportingConversion(String resultCalculationArray, int biggerExponent) {

        double sign = 1;
        if(resultCalculationArray.charAt(0)=='1'){
            sign=-1;
        }

        int exponent = biggerExponent+2-15;
        StringBuilder res = null;


        if(exponent>=0){
            res = new StringBuilder(resultCalculationArray.substring(1));
            res.insert(exponent,".");
        }
        else{
            res = new StringBuilder(resultCalculationArray.substring(1));
            for(int i = exponent; i<0;i++){
                res.insert(0,"0");
            }
            res.insert(0,".");
            res.insert(0,"0");
        }


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


        String result = multiply(firstSignificandForCalculation, secondSignificandForCalculation);

        //reformatting after multiplication


        int biggerExponent = Math.max(firstExponent, secondExponent);

        double supportingConversion = multSupportingConversion(result, biggerExponent);

        return supportingConversion;
    }

    private static double multSupportingConversion(String resultCalculationArray, int biggerExponent) {
        double sign = 1;
        if(resultCalculationArray.charAt(0)=='1'){
            sign=-1;
        }

        int exponent = ((biggerExponent-15)*2)+3;
        StringBuilder res = null;


        if(exponent>=0){
            res = new StringBuilder(resultCalculationArray.substring(1));
            res.insert(exponent,".");
        }
        else{
            res = new StringBuilder(resultCalculationArray.substring(1));
            for(int i = exponent; i<0;i++){
                res.insert(0,"0");
            }
            res.insert(0,".");
            res.insert(0,"0");
        }

        String[] numberParts = res.toString().split("\\.");

        double result =(Converter.binaryToInt(numberParts[0])+Converter.binaryToFraction(numberParts[1]))*sign;

        return result;
    }

    private static String multiply(String firstNumber,String secondNumber){

        String firstNumberToMultiply = firstNumber.substring(1);
        String secondNumberToMultiply = secondNumber.substring(1);

        int biggerLength = Math.max(firstNumberToMultiply.length(),secondNumberToMultiply.length())-1;


        List<String> arrayToAddition = new ArrayList<String>();

        StringBuilder stringToAdd = null;

        String zeroes = "";
        for (int i = 0; i <= biggerLength; i++) {

            zeroes += "0";
        }

        for (int i = firstNumberToMultiply.length() - 1; i >= 0; i--) {

            stringToAdd = new StringBuilder("");
            if (secondNumberToMultiply.charAt(i) == '1') {

                stringToAdd.append(zeroes.substring(0, i));
                stringToAdd.append(firstNumberToMultiply);
                if (!zeroes.substring(0, i).isEmpty()) {
                    stringToAdd.append(zeroes.substring(i));
                }
                arrayToAddition.add(stringToAdd.toString());
            }
        }

        String result = null;

        if(firstNumber.charAt(0)==secondNumber.charAt(0)){
            result = "0";
        }
        else{
            result = "1";
        }
        result += multiplicationSupportAddition(arrayToAddition);

        return result;
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

    public static String multiplicationSupportAddition(List<String> stringList) {

        String result = "";
        int stringLength = stringList.get(0).length();

        for (int i = 0; i < stringLength; i++) {

            result += "0";
        }

        for(String s : stringList){
            result = add(result,s);
        }

        return result.toString();
    }
}

