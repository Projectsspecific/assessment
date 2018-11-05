import com.projectspecific.rpn.RpnException;
import com.projectspecific.rpn.Command;
import com.projectspecific.rpn.Operators;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class CommandTest {

	@Test
	public void testReverseOneOperandInstruction() throws RpnException {

		Operators mockOperator = Mockito.mock(Operators.class);
		when(mockOperator.getOperandsNumber()).thenReturn(1);
		when(mockOperator.getOpposite()).thenReturn("sqrt");

		Random r = new Random();
		Command instruction = new Command(mockOperator, r.nextDouble());

		assertEquals(String.format("%s", mockOperator.getOpposite()),
				instruction.getInstruction());
	}

	@Test
	public void testReverseTwoOperandInstruction() throws RpnException {

		Operators mockOperator = Mockito.mock(Operators.class);
		when(mockOperator.getOperandsNumber()).thenReturn(2);
		when(mockOperator.getOpposite()).thenReturn("-");

		Random r = new Random();
		double value = r.nextDouble();
		Command instruction = new Command(mockOperator, value);

		assertEquals(String.format("%f %s %f", value,
				mockOperator.getOpposite(), value),
				instruction.getInstruction());
	}

	@Test(expected = RpnException.class)
	public void testInvalidOperandsNumber() throws RpnException {
		Operators mockOperator = Mockito.mock(Operators.class);
		when(mockOperator.getOperandsNumber()).thenReturn(0);

		Random r = new Random();
		Command instruction = new Command(mockOperator, r.nextDouble());
		instruction.getInstruction();
	}
}
