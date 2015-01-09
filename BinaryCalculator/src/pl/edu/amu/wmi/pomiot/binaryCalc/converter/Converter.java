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

	public static int binaryToInt(String integer){

		int result = 0;

		for(int i=0;i<=integer.length()-1;i++){

			if(integer.charAt(i) == '1'){
				result += Math.pow(2,integer.length()-1-i);
			}
		}

		return result;
	}

	public static String fractionToBinary(double number){

		double workingNumber = number;
		StringBuilder result = new StringBuilder();

		for(int i=1;i<=20;i++){
			if(workingNumber - Math.pow((0.5), i) >= 0){
				result.append("1");
				workingNumber = (double) (workingNumber - Math.pow(0.5, i));
			}
			else{
				result.append("0");
			}
		}

		return result.toString();
	}

	public static double binaryToFraction(String fraction){

		double result = 0;

		for(int i=0;i<=fraction.length()-1;i++){

			if(fraction.charAt(i) == '1'){
				result += Math.pow((2),-(i+1));
			}
		}

		return result;
	}

	public static double binaryStringToDouble(String numberRepresentation){

		System.out.println();
		System.out.println("-----------\n Adding numbers\n-----------");

		double result = 0;


		String[] numberRepresentationParts = numberRepresentation.split(" ");

		int sign = 1;
		if(numberRepresentationParts[0].equals("1")) sign = -1;
		System.out.println("Sign is: " + sign);

		int exponent = Converter.posBinaryToInt(numberRepresentationParts[1])-15;
		System.out.println("Exponent is: " + exponent);

		StringBuilder significandInProgress = new StringBuilder("1"+numberRepresentationParts[2]);
		System.out.println("Significand before calculation is: " + significandInProgress);

		if(exponent<0){
			significandInProgress.insert(0,"0.");
			for(int i= exponent;i<-1;i++){
				significandInProgress.insert(2,"0");
			}
		}
		else {
			significandInProgress.insert(exponent + 1, ".");
		}
		System.out.println("Significand after dot insertion is: " + significandInProgress);

		String[] integerAndFractionParts = significandInProgress.toString().split("\\.");

		System.out.println("Fraction part is: "+integerAndFractionParts[1]);
		double fractionPart = binaryToFraction(integerAndFractionParts[1]);
		int integerPart = binaryToInt(integerAndFractionParts[0]);

		result = sign*(integerPart+fractionPart);
		System.out.println("Number after conversion is: "+result);
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
}