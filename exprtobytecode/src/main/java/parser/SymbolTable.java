package parser;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by paulovvmelo on 23/02/14.
 */
public class SymbolTable {
    private static Set<String> set;

    static {
        set = new HashSet<String>();
        set.add("sqrt");
        set.add("sin");
        set.add("cos");
        set.add("tan");
    }

    public static boolean contains(String funcName){
        return set.contains(funcName);
    }
}
