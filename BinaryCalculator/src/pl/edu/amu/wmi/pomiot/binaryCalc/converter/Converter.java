package pl.edu.amu.wmi.pomiot.binaryCalc.converter;

public class Converter {

	public static String posIntToBinary(int number){

		StringBuilder result = new StringBuilder("");

		for(int i=9;i>=0;i--){
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

	public static int posBinaryToInt(String binary){

		int result = 0;
		
		for(int i=0;i<=binary.length()-1;i++){
			
			if(binary.charAt(i) == '1'){
				result += Math.pow(2,binary.length()-1-i);
			}
		}

		return result;
	}
}
