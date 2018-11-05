import com.projectspecific.rpn.RpnCalculator;
import com.projectspecific.rpn.RpnException;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RpnCalculatorTest {

	@Test
	public void testAritmeticOperators() throws RpnException {
		RpnCalculator calc = new RpnCalculator();

		calc.eval("5 2");
		assertEquals(5, calc.getStack().get(0), 0);
		assertEquals(2, calc.getItem(1), 0);

		calc.eval("clear");
		calc.eval("5 2 -");
		assertEquals(1, calc.getStack().size());
		assertEquals(3, calc.getItem(0), 0);
		calc.eval("3 -");
		assertEquals(1, calc.getStack().size());
		assertEquals(0, calc.getItem(0), 0);

		calc.eval("clear");
		calc.eval("1 2 3 4 5 *");
		assertEquals(4, calc.getStack().size());
		calc.eval("clear 3 4 -");
		assertEquals(1, calc.getStack().size());
		assertEquals(-1, calc.getItem(0), 0);

		calc.eval("clear");
		calc.eval("7 12 2 /");
		assertEquals(7, calc.getItem(0), 0);
		assertEquals(6, calc.getItem(1), 0);
		calc.eval("*");
		assertEquals(1, calc.getStack().size());
		assertEquals(42, calc.getItem(0), 0);
		calc.eval("4 /");
		assertEquals(1, calc.getStack().size());
		assertEquals(10.5, calc.getItem(0), 0);

		calc.eval("clear");
		calc.eval("1 2 3 4 5");
		calc.eval("* * * *");
		assertEquals(1, calc.getStack().size());
		assertEquals(120, calc.getItem(0), 0);

	}

	@Test
	public void testSqrt() throws RpnException {
		RpnCalculator calc = new RpnCalculator();
		calc.eval("2 sqrt");
		calc.eval("clear 9 sqrt");
		assertEquals(1, calc.getStack().size());
		assertEquals(3, calc.getItem(0), 0);
	}

	@Test
	public void testInsuficientParameters() {
		RpnCalculator calc = new RpnCalculator();
		try {
			calc.eval("1 2 3 * 5 + * * 6 5");
		} catch (RpnException e) {
			assertEquals("operator * (position: 8): insufficient parameters",
					e.getMessage());
		}
		assertEquals(1, calc.getStack().size());
		assertEquals(11, calc.getItem(0), 0);
	}

	@Test
	public void testUndo() throws RpnException {
		RpnCalculator calc = new RpnCalculator();
		calc.eval("5 4 3 2");
		assertEquals(4, calc.getStack().size());
		calc.eval("undo undo *");
		assertEquals(1, calc.getStack().size());
		assertEquals(20, calc.getItem(0), 0);
		calc.eval("5 *");
		assertEquals(1, calc.getStack().size());
		assertEquals(100, calc.getItem(0), 0);
		calc.eval("undo");
		assertEquals(2, calc.getStack().size());
		assertEquals(20, calc.getItem(0), 0);
		assertEquals(5, calc.getItem(1), 0);
		calc.eval("+ undo - undo / undo * undo sqrt undo pow undo");
		assertEquals(2, calc.getStack().size());
		assertEquals(20, calc.getItem(0), 0.0000000001);
		assertEquals(5, calc.getItem(1), 0.0000000001);
	}

	@Test(expected = RpnException.class)
	public void testInvalidCharacters() throws RpnException {
		RpnCalculator calc = new RpnCalculator();
		calc.eval("2 a +");
	}

	@Test(expected = RpnException.class)
	public void testNoSpaces() throws RpnException {
		RpnCalculator calc = new RpnCalculator();
		calc.eval("22+");
	}

	@Test(expected = RpnException.class)
	public void testDivideByZero() throws RpnException {
		RpnCalculator calc = new RpnCalculator();
		calc.eval("1 0 /");
	}

	@Test(expected = RpnException.class)
	public void testNullInput() throws RpnException {
		RpnCalculator calc = new RpnCalculator();
		calc.eval(null);
	}
}
