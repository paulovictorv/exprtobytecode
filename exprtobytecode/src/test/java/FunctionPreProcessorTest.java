import org.junit.Test;
import parser.FunctionPreProcessor;
import parser.exception.NotValidIdException;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by paulovvmelo on 23/02/14.
 */
public class FunctionPreProcessorTest {

    @Test
    public void shouldChangeVariableNames() throws NotValidIdException {
        String function = "x+y";
        FunctionPreProcessor preProc = new FunctionPreProcessor();
        String postProcessed = preProc.preProcess(function);

        assertThat(postProcessed).isEqualTo("$1+$2");
    }

    @Test
    public void shouldOnlyChangeOnce() throws NotValidIdException {
        String function = "x+x";
        FunctionPreProcessor preProc = new FunctionPreProcessor();
        String postProcessed = preProc.preProcess(function);

        assertThat(postProcessed).isEqualTo("$1+$1");
    }

    @Test(expected = NotValidIdException.class)
    public void shouldDetectInvalidId() throws NotValidIdException {
        String function = "x+1x";

        FunctionPreProcessor preProc = new FunctionPreProcessor();

        preProc.preProcess(function);
    }

    @Test
    public void shouldIgnoreMathUtilIdentifiers() throws NotValidIdException {
        String function = "sqrt(x+y)";
        FunctionPreProcessor preProc = new FunctionPreProcessor();
        String postProcessed = preProc.preProcess(function);

        assertThat(postProcessed).isEqualTo("sqrt($1+$2)");
    }



}
