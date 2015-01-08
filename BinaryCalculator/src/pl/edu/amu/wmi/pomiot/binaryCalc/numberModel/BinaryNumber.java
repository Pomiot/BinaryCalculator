package pl.edu.amu.wmi.pomiot.binaryCalc.numberModel;

import pl.edu.amu.wmi.pomiot.binaryCalc.converter.Converter;

public class BinaryNumber {

	/*
	 * String representation of this particular binary number model is:
	 * 
	 * 1 bit for sign
	 * 5 bits for exponent
	 * 10 bits for significand
	 */

	String stringRepresentation = null;
	
	private String signBit = null;
	private String exponentBits = null;
	private String fractionBits = null;
	
	public BinaryNumber(double number){
		
		System.out.println("Entered number equals: "+number);
		buildStringRepresentation(number);
		System.out.println(stringRepresentation);
	}
	
	private void buildStringRepresentation(double number){
		
		StringBuilder result = new StringBuilder("");
		
		//ustawiamy znak liczby
		if (number < 0){
			this.signBit = "1";
			number = Math.abs(number);
			}
		else this.signBit = "0";
		
		//mielimy liczbê zeby wydobyæ wyk³adnik i mantysê
		String numberAsBinaryString = normalize(Converter.posDoubleToString(number));
		
		this.stringRepresentation = numberAsBinaryString.toString();
	}
	
	private String normalize(String representation){
		
		
		//dostajemy gówno z kropk¹, trzeba to skatowaæ
		StringBuilder result = new StringBuilder("");
		
		//wydobywamy wyk³adnik
		int difference = (representation.indexOf('.')-representation.indexOf('1'));
		System.out.println("Difference is: "+difference);
		this.exponentBits = Converter.getExponentBits(difference);
		System.out.println("Exponent bits: "+exponentBits);
		
		
		//teraz trzeba wydobyæ mantysê
		System.out.println("Aktualnie gówno wygl¹da tak: "+representation);
		
		int startOfThings = representation.indexOf('1');
		fractionBits = representation.substring(startOfThings, startOfThings+10);
		System.out.println("Fraction bits: "+fractionBits);
		
		
		
		
		/*
		if(difference>0){
		result.append(representation.substring(representation.indexOf('1')));
		}
		else{
			result.append(representation.substring(representation.indexOf('.')+1));
		}
		
		result.deleteCharAt(result.indexOf("."));
		this.fractionBits = result.substring(0, 9);*/
		
		return result.toString(); 
	}
	
	public String toString(){
		return signBit+" "+exponentBits+" "+fractionBits;
	}

	public String getStringRepresentation(){
		return stringRepresentation;
	}

	public String getSignBit(){
		return this.signBit;
	}
	
	public String getExponentBits(){
		return this.exponentBits;
	}
	
	public String getFractionBits(){
		return this.fractionBits;
	}

	public double getAsDouble(){
		return Converter.convertBinaryNumberToDouble(this);
	}

}
