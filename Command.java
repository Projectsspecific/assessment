package com.projectspecific.rpn;

public class Command {
	Operators operator;
	Double value;

	public Command(Operators operator, Double value) {
		this.operator = operator;
		this.value = value;
	}

	public String getInstruction() throws RpnException {
		if (operator.getOperandsNumber() < 1)
			throw new RpnException(String.format(
					"invalid operation for operator %s", operator.getSymbol()));

		return (operator.getOperandsNumber() < 2) ? String.format("%s",
				operator.getOpposite()) : String.format("%f %s %f", value,
				operator.getOpposite(), value);
	}
}
