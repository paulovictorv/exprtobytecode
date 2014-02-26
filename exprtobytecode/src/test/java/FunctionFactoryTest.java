import exprtob.FunctionFactory;
import exprtob.exception.FunctionCompilerException;
import exprtob.function.Function;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by paulovvmelo on 23/02/14.
 */
public class FunctionFactoryTest {

    private static Function FUNCTION;

    @BeforeClass
    public static void init() throws FunctionCompilerException {
        FUNCTION = FunctionFactory.parse("x+y");
    }

    @Test
    public void shouldCreateFunction() throws FunctionCompilerException {
       FUNCTION.eval(1, 2);
    }

}
