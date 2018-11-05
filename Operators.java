package com.projectspecific.rpn;

import static java.lang.Math.sqrt;

import java.util.HashMap;
import java.util.Map;

public enum Operators {

	ADDITION("+", "-", 2) {
		public Double calculate(Double firstOperand, Double secondOperand)
				throws RpnException {
			return secondOperand + firstOperand;
		}
	},

	SUBTRACTION("-", "+", 2) {
		public Double calculate(Double firstOperand, Double secondOperand) {
			return secondOperand - firstOperand;
		}
	},

	MULTIPLICATION("*", "/", 2) {
		public Double calculate(Double firstOperand, Double secondOperand) {
			return secondOperand * firstOperand;
		}
	},

	DIVISION("/", "*", 2) {
		public Double calculate(Double firstOperand, Double secondOperand)
				throws RpnException {
			if (firstOperand == 0)
				throw new RpnException("Cannot divide by 0.");
			return secondOperand / firstOperand;
		}
	},

	SQUAREROOT("sqrt", "pow", 1) {
		public Double calculate(Double firstOperand, Double secondOperand) {
			return sqrt(firstOperand);
		}
	},

	UNDO("undo", null, 0) {
		public Double calculate(Double firstOperand, Double secondOperand)
				throws RpnException {
			throw new RpnException("Invalid operation");
		}
	},

	CLEAR("clear", null, 0) {
		public Double calculate(Double firstOperand, Double secondOperand)
				throws RpnException {
			throw new RpnException("Invalid operation");
		}
	};

	private static final Map<String, Operators> lookup = new HashMap<String, Operators>();

	static {
		for (Operators o : values()) {
			lookup.put(o.getSymbol(), o);
		}
	}

	private String symbol;
	private String opposite;
	private int operandsNumber;

	Operators(String symbol, String opposite, int operandsNumber) {
		this.symbol = symbol;
		this.opposite = opposite;
		this.operandsNumber = operandsNumber;
	}

	public static Operators getEnum(String value) {
		return lookup.get(value);
	}

	public abstract Double calculate(Double firstOperand, Double secondOperand)
			throws RpnException;

	public String getSymbol() {
		return symbol;
	}

	public String getOpposite() {
		return opposite;
	}

	public int getOperandsNumber() {
		return operandsNumber;
	}

	@Override
	public String toString() {
		return symbol;
	}
}