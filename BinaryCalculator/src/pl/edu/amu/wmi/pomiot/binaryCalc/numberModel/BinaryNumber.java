package pl.edu.amu.wmi.pomiot.binaryCalc.numberModel;

import pl.edu.amu.wmi.pomiot.binaryCalc.converter.Converter;

public class BinaryNumber {

	/**
	 * String representation of this particular binary number model is:
	 * 
	 * 1 bit for sign
	 * 5 bits for exponent
	 * 10 bits for significand
	 **/

	String stringRepresentation = null;
	
	private String signBit = null;
	private String exponentBits = null;
	private String significandBits = null;
	
	public BinaryNumber(double number){
		
		System.out.println("Entered number equals: "+number);
		buildStringRepresentation(number);
		//System.out.println(stringRepresentation);
	}
	
	private void buildStringRepresentation(double number){

		double workingNumber = number;

		//ustawiamy znak liczby
		this.signBit = getSignBit(workingNumber);
		workingNumber = Math.abs(workingNumber);

		String posDoubleAsString = Converter.posDoubleToString(workingNumber);

		this.exponentBits = getExponentBits(posDoubleAsString);
		System.out.println("Exponent bits: "+exponentBits);

		this.significandBits = getSignificadBits(posDoubleAsString);
		System.out.println("Significand bits: "+significandBits);

	}

	private String getSignBit(double number){
		if (number>=0) return "0";
		else return "1";
	}

	private String getExponentBits(String numberBin){

		String exponentBits = "";

		String[] parts = numberBin.split("\\.");

		String integerPart = parts[0];
		String fractionPart = parts[1];


		String integerPartWithoutPrecedingZeroes = (new Integer(Integer.parseInt(integerPart))).toString();
		StringBuilder helper = new StringBuilder(fractionPart);
		String fractionPartWithoutFollowingZeroes = helper.substring(0,20);


		if(integerPartWithoutPrecedingZeroes.length()>1){

			// number is greater or equal 2

			exponentBits = Converter.intToBinary((integerPartWithoutPrecedingZeroes.length() - 1) + 15);
			exponentBits = exponentBits.substring(exponentBits.length()-5);
		}
		else if(integerPartWithoutPrecedingZeroes.equals("1")){

			// number is greater or equal 1 but lesser than 2

			exponentBits = Converter.intToBinary(15);
			exponentBits = exponentBits.substring(exponentBits.length()-5);
		}
		else {

			// number is lesser than 1

			int firstOccurenceOfOne = fractionPartWithoutFollowingZeroes.indexOf("1");

			exponentBits = Converter.intToBinary(15 - 1 - firstOccurenceOfOne);
			exponentBits = exponentBits.substring(exponentBits.length() - 5);
		}
		return exponentBits;
	}

	private String getSignificadBits(String numberBin){

		String significandBits = "";

		String[] parts = numberBin.split("\\.");

		String integerPart = parts[0];
		String fractionPart = parts[1];

		String integerPartWithoutPrecedingZeroes = (new Integer(Integer.parseInt(integerPart))).toString();

		StringBuilder helper = new StringBuilder(fractionPart);

		//helper.reverse();

		//helper = new StringBuilder((new Integer(Integer.parseInt(helper.toString()))).toString());

		String fractionPartWithoutFollowingZeroes = helper.toString().substring(0,10); //(helper.reverse()).toString();

		StringBuilder significand = new StringBuilder(integerPartWithoutPrecedingZeroes+fractionPartWithoutFollowingZeroes);


		int firstOccurenceOfOne = significand.indexOf("1");

		if(firstOccurenceOfOne<0){
			firstOccurenceOfOne = 0;
		}

		significand.append("0000000000");
		significandBits = significand.substring(firstOccurenceOfOne+1,firstOccurenceOfOne+11);

		return significandBits;
	}

	public String toString(){

		return signBit+" "+exponentBits+" "+significandBits;
	}
}
