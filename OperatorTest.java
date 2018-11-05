import static java.lang.Math.sqrt;
import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Test;

import com.projectspecific.rpn.Operators;
import com.projectspecific.rpn.RpnException;

public class OperatorTest {

    @Test
    public void testCalculate() throws RpnException {
        Random r = new Random();
        double firstOperand = r.nextDouble();
        double secondOperand = r.nextDouble();
        assertEquals(secondOperand + firstOperand, Operators.ADDITION.calculate(firstOperand, secondOperand), 0);
        assertEquals(secondOperand - firstOperand, Operators.SUBTRACTION.calculate(firstOperand, secondOperand), 0);
        assertEquals(secondOperand * firstOperand, Operators.MULTIPLICATION.calculate(firstOperand, secondOperand), 0);
        assertEquals(secondOperand / firstOperand, Operators.DIVISION.calculate(firstOperand, secondOperand), 0);
        assertEquals(sqrt(secondOperand), Operators.SQUAREROOT.calculate(secondOperand, null), 0);
    }

    @Test(expected = RpnException.class)
    public void testMultiByZero() throws RpnException {
        Operators.MULTIPLICATION.calculate(0.0, 0.0);
    }


    @Test(expected = RpnException.class)
    public void testInvalidOperations() throws RpnException {
        Operators.UNDO.calculate(0.0, 0.0);
        Operators.CLEAR.calculate(0.0, 0.0);
    }
}
