package pl.edu.amu.wmi.pomiot.binaryCalc.numberModel;

import pl.edu.amu.wmi.pomiot.binaryCalc.converter.Converter;

/**
 * Created by Tomasz on 2015-01-08.
 */
public class Operations {

    public static String addition(String firstNumber, String secondNumber) {

        /**
         *
         * [0] znak
         * [1] wykładnik
         * [2] mantysa
         *
         **/

        System.out.println();
        System.out.println("-----------\n Adding numbers\n-----------");
        System.out.println("Fist number in addition is: " + firstNumber);
        System.out.println("Second number in addition is: " + secondNumber);

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
            firstSignificandForCalculation += "1";
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

        System.out.println("First element after preparation to addition:  " + firstSignificandForCalculation);
        System.out.println("Second element after preparation to addition: " + secondSignificandForCalculation);

        String result = add(firstSignificandForCalculation, secondSignificandForCalculation);

        //reformatting after addition

        String reformattedSign = result.substring(0,1);
        String reformattedSignificand = result.substring(1)+"0";
        int biggerExponent = Math.max(firstExponent, secondExponent);


        if(reformattedSignificand.charAt(0)=='1'){
            reformattedSignificand=reformattedSignificand.substring(1)+"0";
            biggerExponent++;
        }
        else{
            reformattedSignificand=reformattedSignificand.substring(1)+"0";
        }
        if(reformattedSignificand.charAt(0)=='1'){
            reformattedSignificand=reformattedSignificand.substring(1)+"0";
        }

        String reformattedExponent = Converter.intToBinary(biggerExponent);
        reformattedExponent = reformattedExponent.substring(reformattedExponent.length()-5);

        String finalResult = reformattedSign+" "+reformattedExponent+" "+reformattedSignificand;

        return finalResult;
    }

    private static String add(String firstNumber, String secondNumber){

        char[] firstCalculationArray = firstNumber.toCharArray();
        char[] secondCalculationArray = secondNumber.toCharArray();
        StringBuilder resultCalculationArray = new StringBuilder("");

        int carryBit = 0;

        int bitAtNumberOne =0;
        int bitAtNumberTwo =0;

        for(int i = firstCalculationArray.length-2; i>=0; i--){

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

        System.out.println("\nThe result of addition is: ");
        resultCalculationArray.reverse();
        System.out.println(resultCalculationArray);

        return resultCalculationArray.toString();
    }
}
