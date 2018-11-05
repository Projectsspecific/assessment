package com.projectspecific.rpn;

import java.text.DecimalFormat;
import java.io.Console;
import java.util.Stack;

public class Main {

	public static void main(String[] args) {

		RpnCalculator calc = new RpnCalculator();

		Console console = System.console();

		boolean input = true;
		while (input) {
			String inputString = console.readLine(": ");
			if ("exit".equals(inputString)) {
				input = false;
			} else {
				try {
					calc.eval(inputString);
				} catch (RpnException e) {
					System.out.println(e.getMessage());
				}

				DecimalFormat fmt = new DecimalFormat("0.##########");
				Stack<Double> stack = calc.getStack();
				System.out.print("Stack: ");
				for (Double value : stack) {
					System.out.print(fmt.format(value));
					System.out.print(" ");
				}
				System.out.printf("%n");
			}
		}
	}
}
