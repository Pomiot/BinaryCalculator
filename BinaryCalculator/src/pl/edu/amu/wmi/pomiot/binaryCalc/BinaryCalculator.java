package pl.edu.amu.wmi.pomiot.binaryCalc;

import pl.edu.amu.wmi.pomiot.binaryCalc.numberModel.BinaryNumber;

public class BinaryCalculator {
	
	public static void main(String[] args){
		
		BinaryNumber numb = new BinaryNumber(1.5);
		
		System.out.println("Entered after conversions equals: "+numb);
	}
}
