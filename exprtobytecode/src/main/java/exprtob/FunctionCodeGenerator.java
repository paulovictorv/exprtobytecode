package exprtob;

import exprtob.exception.FunctionParserException;
import exprtob.parser.FunctionParser;
import exprtob.parser.FunctionParserFactory;
import exprtob.parser.ParserSettings;

/**
 * Created by Paulo on 26/02/14.
 */
public class FunctionCodeGenerator {
    public static String generate(String function) throws FunctionParserException {
        FunctionParser parser = FunctionParserFactory.get(ParserSettings.DEFAULT);

        String parse = parser.parse(function);

        StringBuilder builder = new StringBuilder();

        builder.append("public double evalArray(double[] params){return(");
        builder.append(parse);
        builder.append(");}");

        return builder.toString();
    }
}
