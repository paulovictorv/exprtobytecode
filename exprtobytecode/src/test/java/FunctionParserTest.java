import org.junit.Test;
import parser.Function;
import parser.FunctionParser;
import parser.FunctionParserFactory;
import parser.ParserSettings;
import parser.exception.FunctioNotDefined;
import parser.exception.FunctionParserException;
import parser.exception.NotValidIdException;

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
            Function function = parser.parse("x+y");

            assertThat(function).isNotNull();
        } catch (Exception e){
            fail();
        }

    }

    @Test(expected = FunctionParserException.class)
    public void shouldFailToParseFunction(){
        FunctionParser parser = FunctionParserFactory.get(ParserSettings.DEFAULT);
        Function function = parser.parse("x+");
    }

    @Test(expected = NotValidIdException.class)
    public void shouldThrowNotIDError(){
        FunctionParser parser = FunctionParserFactory.get(ParserSettings.DEFAULT);
        Function function = parser.parse("1x+y");
    }

    @Test(expected = FunctioNotDefined.class)
    public void shouldThrowNotValidFuncError(){
        FunctionParser parser = FunctionParserFactory.get(ParserSettings.DEFAULT);
        Function function = parser.parse("x+foo(y)");
    }

}
