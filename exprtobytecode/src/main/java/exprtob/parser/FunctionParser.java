package exprtob.parser;

import exprtob.exception.FunctionParserException;

/**
 * Created by paulovvmelo on 23/02/14.
 */
public interface FunctionParser {
    public String parse(String s ) throws FunctionParserException;
}
