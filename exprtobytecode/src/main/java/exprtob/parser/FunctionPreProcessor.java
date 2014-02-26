package exprtob.parser;

import exprtob.exception.FunctionParserException;
import exprtob.exception.NotValidIdException;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class responsible for pre processing an function expression to Javassist friendly expression. It substitutes
 * identifiers with $1 and so on.
 * Created by paulovvmelo on 23/02/14.
 */
public class FunctionPreProcessor implements FunctionParser {

    Pattern pattern;
    //This pattern also matches invalid id's which starts with numbers.
    String thePattern = "\\b[0-9]*[_a-zA-Z][_a-zA-Z0-9]*";


    public FunctionPreProcessor(){
        pattern =  Pattern.compile(thePattern);
    }

    /**
     * Parses a function, substituting variable definitions with variable markers such as $1, $2 so it can work with
     * javassist expression compiler. It uses a c-like id regex to identify valid ids.
     *
     * @param function string representing the function to be parsed
     * @return parsed string
     * @throws NotValidIdException if there's an invalid id
     */
    public String parse(String function) throws FunctionParserException {
        Map<String, Integer> mapOfVariables = new HashMap<String, Integer>();

        Matcher matcher = pattern.matcher(function);

        StringBuffer sb = new StringBuffer();
        Integer previousInteger = -1;
        Integer lastHitIndex = 0;

        while(matcher.find()){ //While there is a match inside the function...
            String variable = matcher.group();

            if (SymbolTable.contains(variable)){ //If this identifier is in this table, it's either a custom func or Math func
                continue;
            }

            if (variable.matches("\\d+.*")){ //Tests if the id starts with numbers. This might be inneficient.
                throw new NotValidIdException(variable, matcher.start());
            }

            Integer integer = mapOfVariables.get(variable);

            if (integer == null){
                integer = ++previousInteger;
                mapOfVariables.put(variable, integer);
            }

            String newName = "params[" + integer.toString() + "]";
            matcher.appendReplacement(sb, newName);
            lastHitIndex = matcher.end();
        }

        if (lastHitIndex < function.length()){ //If the last index that had a match is less then the string size,
                                               //there is still characters left to be passed on to the parsed string.
            sb.append(function.substring(lastHitIndex)); //Append the rest to the StringBuilder
        }

        return sb.toString();
    }
}
