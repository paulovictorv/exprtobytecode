package exprtob;

import exprtob.exception.FunctionCompilerException;
import exprtob.exception.FunctionParserException;
import exprtob.function.Function;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtNewMethod;

/**
 * Created by Paulo on 26/02/14.
 */
public class FunctionCompiler {

    public Function compile(String function) throws FunctionCompilerException {
        try {
            ClassPool pool = ClassPool.getDefault();
            CtClass functionClass = pool.makeClass("UserFunction");

            String generatedCode = FunctionCodeGenerator.generate(function);

            functionClass.addMethod(
                    CtNewMethod.make(generatedCode,
                    functionClass)
                    );

            functionClass.setSuperclass(pool.get("exprtob.function.BaseFunction"));

            Class aClass = functionClass.toClass();
            Function o = (Function) aClass.newInstance();

            return o;
        } catch (Exception e){
            throw new FunctionCompilerException(e);
        } catch (FunctionParserException e) {
            throw new FunctionCompilerException(e);
        }
    }
}
