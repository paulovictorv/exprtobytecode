package exprtob;

import exprtob.exception.FunctionCompilerException;
import exprtob.function.Function;

/**
 * Created by paulovvmelo on 23/02/14.
 */
public class FunctionFactory {

    public static Function parse(String s) throws FunctionCompilerException {
        FunctionCompiler compiler = new FunctionCompiler();
        return compiler.compile(s);
    }

}
