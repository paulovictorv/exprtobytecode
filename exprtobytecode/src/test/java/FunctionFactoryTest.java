import org.junit.Test;
import parser.Function;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by paulovvmelo on 23/02/14.
 */
public class FunctionFactoryTest {

    @Test
    public void shouldCreateFunction(){
        Function function = FunctionFactory.parse("x+y");

        //Assertions
        assertThat(function).isNotNull();
    }

}
