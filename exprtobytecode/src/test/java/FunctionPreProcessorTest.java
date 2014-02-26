import exprtob.exception.FunctionParserException;
import exprtob.exception.NotValidIdException;
import exprtob.parser.FunctionPreProcessor;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by paulovvmelo on 23/02/14.
 */
public class FunctionPreProcessorTest {

    @Test
    public void shouldChangeVariableNames() throws FunctionParserException {
        String function = "x+y";
        FunctionPreProcessor preProc = new FunctionPreProcessor();
        String postProcessed = preProc.parse(function);

        assertThat(postProcessed).isEqualTo("params[0]+params[1]");
    }

    @Test
    public void shouldOnlyChangeOnce() throws FunctionParserException {
        String function = "x+x";
        FunctionPreProcessor preProc = new FunctionPreProcessor();
        String postProcessed = preProc.parse(function);

        assertThat(postProcessed).isEqualTo("params[0]+params[0]");
    }

    @Test(expected = NotValidIdException.class)
    public void shouldDetectInvalidId() throws FunctionParserException {
        String function = "x+1x";

        FunctionPreProcessor preProc = new FunctionPreProcessor();

        preProc.parse(function);
    }

    @Test
    public void shouldIgnoreMathUtilIdentifiers() throws FunctionParserException {
        String function = "sqrt(x+y)";
        FunctionPreProcessor preProc = new FunctionPreProcessor();
        String postProcessed = preProc.parse(function);

        assertThat(postProcessed).isEqualTo("sqrt(params[0]+params[1])");
    }



}
