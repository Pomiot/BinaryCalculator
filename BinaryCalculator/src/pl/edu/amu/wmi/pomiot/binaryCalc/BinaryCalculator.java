package pl.edu.amu.wmi.pomiot.binaryCalc;

import pl.edu.amu.wmi.pomiot.binaryCalc.converter.Converter;

public class BinaryCalculator {
	
	public static void main(String[] args){
		
		System.out.println(Converter.posIntToBinary(7));
		System.out.println(Converter.posBinaryToInt("11111"));
	}

}
