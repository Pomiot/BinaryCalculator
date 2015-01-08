package pl.edu.amu.wmi.pomiot.binaryCalc.converter;

import pl.edu.amu.wmi.pomiot.binaryCalc.numberModel.BinaryNumber;

public class Converter {

	public static String posIntToBinary(int number){

		StringBuilder result = new StringBuilder("");

		for(int i=19;i>=0;i--){
			if(number - Math.pow(2, i) >= 0){
				result.append("1");
				number = (int) (number - Math.pow(2, i));
			}
			else{
				result.append("0");
			}
		}

		return result.toString();
	}

	public static String posDoubleToString(double number){

		int tempNumb = (int)Math.floor(number*Math.pow(2, 10));
		String multipliedValueString = posIntToBinary(tempNumb);

		int stringLength = multipliedValueString.length();
		StringBuilder result = new StringBuilder("");
		result.append(multipliedValueString.substring(0,stringLength-10)).append(".").append(multipliedValueString.substring(stringLength-10));

		return result.toString();
	}

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
		
		System.out.println("Pracujê na nastêpuj¹cym stringu: " + binary);

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

	public static double convertBinaryNumberToDouble(BinaryNumber number){

		double sign = 1;
		double exponent = 0;
		double fraction = 0;

		if(number.getSignBit().equals("1")){
			sign=(-1);
		}
		exponent = posBinaryToInt(number.getExponentBits());
		fraction = posStringToFraction(number.getFractionBits());
		System.out.println("Exponent in decimal equals: "+exponent+"\nFraction in decimal equals: "+fraction);

		return sign*(Math.pow(2, exponent-15)*fraction);
	}
}