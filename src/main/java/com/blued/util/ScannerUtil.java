package com.blued.util;

import java.math.BigDecimal;
import java.util.Scanner;

public class ScannerUtil {

	static Scanner scanner = new Scanner(System.in);

	public static int getInput(int max) {
		int input = -1;

		while (input < 0 || input > max) {
			System.out.println("Yarr! Pick a number from 0 to " + max + "!");
			if (!scanner.hasNextInt()) {
				scanner.nextLine();
				continue;
			}
			input = scanner.nextInt();
		}

		return input;
	}

	public static double getWithdrawInput(double max) {
		double input = -1;

		while (input < 0) {
			System.out.println("Yarr! Pick a number from 0 to " + max + "!");
			if (!scanner.hasNextDouble()) {
				scanner.nextLine();
				continue;
			}
			input = scanner.nextDouble();
		}

		return input;

	}

	public static String getStringInput() {
		String input = "";
		while (input.isEmpty()) {
			input = scanner.nextLine();
//			scanner.nextLine();
		}
		return input;
	}

	public static double getInput(double max) {
		double input = -1;

		while (input < 0 || input > max) {
			System.out.println("Yarr! Pick a number from 0 to " + max + "!");
			if (!scanner.hasNextDouble()) {
				scanner.nextLine();
				continue;
			}
			input = scanner.nextDouble();
		}

		return input;
	}

}
