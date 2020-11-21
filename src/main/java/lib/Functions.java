package lib;

import java.util.HashMap;
import java.util.Map;

public class Functions {
    public static final NumberValue ZERO = new NumberValue(0.0);
    private static Map<String, Function> functions;

    static {
        functions = new HashMap<>();
        functions.put("sin", new Function() {
            @Override
            public Value execute(Value... args) {
                if (args.length != 1) throw new RuntimeException("One arg expected");
                return new NumberValue(Math.sin(args[0].asNumber()));
            }
        });
        functions.put("cos", new Function() {
            @Override
            public Value execute(Value... args) {
                if (args.length != 1) throw new RuntimeException("One arg expected");
                return new NumberValue(Math.cos(args[0].asNumber()));
            }
        });
        functions.put("echo", new Function() {
            @Override
            public Value execute(Value... args) {
                for (Value arg : args) {
                    System.out.println(arg);
                }
                return ZERO;
            }
        });
    }

    public static boolean isExists(String key) {
        return functions.containsKey(key);
    }

    public static Function get(String key) {
        if (!isExists(key)) {
            throw new RuntimeException("Unknown function" + key);
        }
        return functions.get(key);
    }

    public static void set(String key, Function function) {
        functions.put(key, function);
    }
}
