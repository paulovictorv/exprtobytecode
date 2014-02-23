package parser.exception;

/**
 * Created by paulovvmelo on 23/02/14.
 */
public class NotValidIdException extends FunctionParserException {
    public NotValidIdException(String variable, int start) {
        super("Invalid identifier " + variable + " at char position " + start);
    }
}
