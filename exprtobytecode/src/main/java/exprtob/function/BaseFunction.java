package exprtob.function;

/**
 * Created by Paulo on 26/02/14.
 */
public abstract class BaseFunction implements Function{

    public BaseFunction(){
    }

    @Override
    public double eval(double... params) {
        return evalArray(params);
    }

    protected abstract double evalArray(double[] params);
}
