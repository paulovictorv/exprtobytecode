package parser;

import parser.exception.NotValidIdException;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class responsible for pre processing an function expression to Javassist friendly expression. It substitutes
 * identifiers with $1 and so on.
 * Created by paulovvmelo on 23/02/14.
 */
public class FunctionPreProcessor {

    Pattern pattern;
    String thePattern = "\\b[0-9]*[_a-zA-Z][_a-zA-Z0-9]*";


    public FunctionPreProcessor(){
        pattern =  Pattern.compile(thePattern);
    }

    public String preProcess(String function) throws NotValidIdException {
        Map<String, Integer> mapOfVariables = new HashMap<String, Integer>();

        Matcher matcher = pattern.matcher(function);

        StringBuffer sb = new StringBuffer();
        Integer previousInteger = 0;
        Integer lastHitIndex = 0;

        while(matcher.find()){
            String variable = matcher.group();

            if (SymbolTable.contains(variable)){
                continue;
            }

            if (variable.matches("\\d+.*")){
                throw new NotValidIdException(variable, matcher.start());
            }

            Integer integer = mapOfVariables.get(variable);

            if (integer == null){
                integer = ++previousInteger;
                mapOfVariables.put(variable, integer);
            }

            String newName = "\\$" + integer.toString();
            matcher.appendReplacement(sb, newName);
            lastHitIndex = matcher.end();
        }

        if (lastHitIndex < function.length()){
            sb.append(function.substring(lastHitIndex));
        }

        return sb.toString();
    }
}
