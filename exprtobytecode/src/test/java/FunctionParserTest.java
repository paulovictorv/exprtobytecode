import exprtob.exception.FunctioNotDefined;
import exprtob.exception.FunctionParserException;
import exprtob.exception.NotValidIdException;
import exprtob.parser.FunctionParser;
import exprtob.parser.FunctionParserFactory;
import exprtob.parser.ParserSettings;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.assertions.Fail.fail;

/**
 * Created by paulovvmelo on 23/02/14.
 */
public class FunctionParserTest {

    @Test
    public void shouldSuccessfullyParseFunction(){
        try {
            FunctionParser parser = FunctionParserFactory.get(ParserSettings.DEFAULT);
            String function = parser.parse("x+y");

            assertThat(function).isNotNull();
        } catch (Exception e){
            fail();
        } catch (FunctionParserException e) {
            fail();
        }

    }

    @Test(expected = FunctionParserException.class)
    public void shouldFailToParseFunction() throws FunctionParserException {
        FunctionParser parser = FunctionParserFactory.get(ParserSettings.DEFAULT);
        String function = parser.parse("x+");
    }

    @Test(expected = NotValidIdException.class)
    public void shouldThrowNotIDError() throws FunctionParserException {
        FunctionParser parser = FunctionParserFactory.get(ParserSettings.DEFAULT);
        String function = parser.parse("1x+y");
    }

    @Test(expected = FunctioNotDefined.class)
    public void shouldThrowNotValidFuncError() throws FunctionParserException {
        FunctionParser parser = FunctionParserFactory.get(ParserSettings.DEFAULT);
        String function = parser.parse("x+foo(y)");
    }

}
