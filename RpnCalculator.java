package com.projectspecific.rpn;

import java.util.Stack;

public class RpnCalculator {

	private Stack<Double> stack = new Stack<Double>();
	private Stack<Command> instStack = new Stack<Command>();
	private int counter = 0;

	private Double parse(String str) {
		try {
			return Double.parseDouble(str);
		} catch (NumberFormatException nfe) {
			return null;
		}
	}

	private void checkToken(String token, boolean isUndoOperation)
			throws RpnException {
		Double value = parse(token);
		if (value == null) {
			checkOperator(token, isUndoOperation);
		} else {

			stack.push(Double.parseDouble(token));
			if (!isUndoOperation) {
				instStack.push(null);
			}
		}
	}

	private void checkOperator(String operatorString, boolean isUndoOperation)
			throws RpnException {

		if (stack.isEmpty()) {
			throw new RpnException("empty stack");
		}

		Operators operator = Operators.getEnum(operatorString);
		if (operator == null) {
			throw new RpnException("invalid operator");
		}

		if (operator == Operators.CLEAR) {
			clear();
			return;
		}

		if (operator == Operators.UNDO) {
			undoExpression();
			return;
		}

		if (operator.getOperandsNumber() > stack.size()) {
			throwInvalidOperand(operatorString);
		}

		Double firstOperand = stack.pop();
		Double secondOperand = (operator.getOperandsNumber() > 1) ? stack.pop()
				: null;

		Double result = operator.calculate(firstOperand, secondOperand);

		if (result != null) {
			stack.push(result);
			if (!isUndoOperation) {
				instStack.push(new Command(Operators.getEnum(operatorString),
						firstOperand));
			}
		}

	}

	private void undoExpression() throws RpnException {
		if (instStack.isEmpty()) {
			throw new RpnException("no operations to undo");
		}

		Command inst = instStack.pop();
		if (inst == null) {
			stack.pop();
		} else {
			eval(inst.getInstruction(), true);
		}
	}

	private void clear() {
		stack.clear();
		instStack.clear();
	}

	private void throwInvalidOperand(String operator) throws RpnException {
		throw new RpnException(String.format(
				"operator %s (position: %d): insufficient parameters",
				operator, counter));
	}

	public Stack<Double> getStack() {
		return stack;
	}

	public Double getItem(int index) {
		return stack.get(index);
	}

	public void eval(String input) throws RpnException {
		eval(input, false);
	}

	private void eval(String input, boolean isUndoOperation)
			throws RpnException {
		if (input == null) {
			throw new RpnException("Input cannot be null.");
		}
		counter = 0;
		String[] result = input.split("\\s");
		for (String aResult : result) {
			counter++;
			checkToken(aResult, isUndoOperation);
		}
	}
}
