package pl.edu.amu.wmi.pomiot.binaryCalc;

import pl.edu.amu.wmi.pomiot.binaryCalc.converter.Converter;
import pl.edu.amu.wmi.pomiot.binaryCalc.numberModel.BinaryNumber;
import pl.edu.amu.wmi.pomiot.binaryCalc.numberModel.Operations;

public class BinaryCalculator {
	
	public static void main(String[] args){
		
		BinaryNumber numb1 = new BinaryNumber(86.125);
		BinaryNumber numb2 = new BinaryNumber(13.125);

		//Converter.binaryStringToDouble(numb2.toString());

		String operationResult = Operations.addition(numb1.toString(), numb2.toString());

		System.out.println("Binarny wynik to chyba: "+operationResult);
		System.out.println("Normalny wynik to chyba: "+Converter.binaryStringToDouble(operationResult));
	}
}
