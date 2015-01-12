package pl.edu.amu.wmi.pomiot.binaryCalc;

import pl.edu.amu.wmi.pomiot.binaryCalc.converter.Converter;
import pl.edu.amu.wmi.pomiot.binaryCalc.numberModel.BinaryNumber;
import pl.edu.amu.wmi.pomiot.binaryCalc.numberModel.Operations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BinaryCalculator {


	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {

		BinaryNumber numb1 = new BinaryNumber(24.47);
		BinaryNumber numb2 = new BinaryNumber(100.17);

		double operationResult = Operations.addition(numb1.toString(), numb2.toString());
		System.out.println("Wynik: "+operationResult );

		System.out.println("****************************");
		System.out.println("Binarny kalkulator");

		boolean running = true;
		while (running) {
			System.out.println("****************************");
			System.out.println("Jaka operacje chcesz wykonac?");
			System.out.println("****************************");

			System.out.println("1. Dodawanie");
			System.out.println("2. Odejmowanie");
			System.out.println("3. Mnożenie [TODO]");

			int choice = Integer.parseInt(reader.readLine());
			switch (choice) {
				case 1: {
					addUI();
					break;
				}
				case 2: {
					subUI();
					break;
				}
				default: {
					System.out.println("Wrong command.");
					break;
				}
			}
		}
	}

	private static void subUI() throws IOException {
		System.out.println();
		System.out.println("Podaj pierwsza liczbe: ");
		double numberOne = Double.parseDouble(reader.readLine());

		System.out.println("Podaj druga liczbe: ");
		double numberTwo = Double.parseDouble(reader.readLine());


		BinaryNumber numb1 = new BinaryNumber(numberOne);
		BinaryNumber numb2 = new BinaryNumber(numberTwo);
		double result = Operations.substraction(numb1.toString(), numb2.toString());
	}

	private static void addUI() throws IOException {
		System.out.println();
		System.out.println("Podaj pierwsza liczbe: ");
		double numberOne = Double.parseDouble(reader.readLine());

		System.out.println("Podaj druga liczbe: ");
		double numberTwo = Double.parseDouble(reader.readLine());

		BinaryNumber numb1 = new BinaryNumber(numberOne);
		BinaryNumber numb2 = new BinaryNumber(numberTwo);
		double result = Operations.addition(numb1.toString(), numb2.toString());
	}

	private static void mulUI() throws IOException {
		System.out.println();
		System.out.println("Podaj pierwsza liczbe: ");
		double numberOne = Double.parseDouble(reader.readLine());

		System.out.println("Podaj druga liczbe: ");
		double numberTwo = Double.parseDouble(reader.readLine());

		BinaryNumber numb1 = new BinaryNumber(numberOne);
		BinaryNumber numb2 = new BinaryNumber(numberTwo);
		double result = Operations.multiplication(numb1.toString(), numb2.toString());

	}
}