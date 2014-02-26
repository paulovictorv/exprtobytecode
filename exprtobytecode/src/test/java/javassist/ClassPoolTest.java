package javassist;

import exprtob.function.Function;
import org.junit.Test;

/**
 * Created by Paulo on 25/02/14.
 */
public class ClassPoolTest {

    @Test
    public void testJavassist() throws NotFoundException, CannotCompileException, IllegalAccessException, InstantiationException {
        ClassPool pool = ClassPool.getDefault();
        CtClass function = pool.makeClass("UserFunction");

        function.addMethod( CtNewMethod.make("protected double evalArray(double[] args){ return (args[1]); }", function) );

        function.setSuperclass(pool.get("exprtob.function.BaseFunction"));

        Class aClass = function.toClass();
        Function o = (Function) aClass.newInstance();

        System.out.println(o.eval(1, 2));
    }

}
