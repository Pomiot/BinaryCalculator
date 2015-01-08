package pl.edu.amu.wmi.pomiot.binaryCalc.converter;

import pl.edu.amu.wmi.pomiot.binaryCalc.numberModel.BinaryNumber;

public class Converter {

	public static String posDoubleToString(double number){

		StringBuilder result = new StringBuilder("");

		double integer =Math.floor(number);
		double fraction=number%1.0;

		System.out.println("Iteger part: "+integer+" Fraction part:"+fraction);
		System.out.println("Attempting to convert to string.");
		System.out.println();

		String integerAsString = intToBinary((int)integer);
		String fractionAsString = fractionToBinary(fraction);

		System.out.println("Integer part as binary: "+integerAsString+" Fraction part as binary:"+fractionAsString);

		result.append(integerAsString+"."+fractionAsString);

		return result.toString();
	}

	public static String intToBinary(int number){

		double workingNumber = number;
		StringBuilder result = new StringBuilder("");

		for(int i=20;i>=0;i--){
			if(workingNumber - Math.pow(2, i) >= 0){
				result.append("1");
				workingNumber = (int) (workingNumber - Math.pow(2, i));
			}
			else{
				result.append("0");
			}
		}

		return result.toString();
	}

	public static String fractionToBinary(double number){

		double workingNumber = number;
		StringBuilder result = new StringBuilder();

		for(int i=1;i<=20;i++){
			if(workingNumber - Math.pow((0.5), i) >= 0){
				result.append("1");
				workingNumber = (double) (workingNumber - Math.pow(2, i));
			}
			else{
				result.append("0");
			}
		}

		return result.toString();
	}

	public static double binaryStringToDouble(String numberRepresentation){

		return 0;
	}




/*
	public static double posStringToFraction(String binary){

		double result = 0;

		for(int i=0;i<=binary.length()-1;i++){

			if(binary.charAt(i) == '1'){
				result += Math.pow((1.0f/2.0f),i+1);
			}
		}

		return result;
	}

	public static int posBinaryToInt(String binary){
		
		System.out.println("Pracuje na nastepujacym stringu: " + binary);

		int result = 0;

		for(int i=0;i<=binary.length()-1;i++){

			if(binary.charAt(i) == '1'){
				result += Math.pow(2,binary.length()-1-i);
			}
		}

		return result;
	}

	public static String getExponentBits(int exponent){

		return posIntToBinary(15+exponent).substring(15);
	}

	public static double convertHalfPrecNumberToDouble(BinaryNumber number){

		double sign = 1;
		int exponent = posBinaryToInt(number.getExponentBits())-15;
		double result = 0;

		if(number.getSignBit().equals("1")){
			sign=(-1);
		}

		if(exponent<0) {
			String temp ="";
			for(int i=exponent;i<0;i++){
				temp = temp + "0";
			}
			result = posStringToFraction(number.getFractionBits());
		}
		return sign*result;
	}
	*/
}