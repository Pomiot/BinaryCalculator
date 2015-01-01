package pl.edu.amu.wmi.pomiot.binaryCalc.numberModel;

public class BinaryNumber {

	/*
	 * String representation of this particular binary number model is:
	 * 
	 * 1 bit for sign
	 * 5 bits for exponent
	 * 10 bits for significand
	 */

	String stringRepresentation = null;

	BinaryNumber(double number){

		StringBuilder result = new StringBuilder("");
		
		//adding sign bit
		if (number < 0) result.append("1");
		else result.append("0");

		
		
		this.stringRepresentation=result.toString();
	}

}
