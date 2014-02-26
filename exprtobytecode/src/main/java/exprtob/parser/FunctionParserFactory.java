package exprtob.parser;

/**
 * Created by paulovvmelo on 23/02/14.
 */
public class FunctionParserFactory {
    public static FunctionParser get(ParserSettings s){
        return new FunctionPreProcessor();
    }
}
