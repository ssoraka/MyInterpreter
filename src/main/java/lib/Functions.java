package lib;

import java.util.HashMap;
import java.util.Map;

public class Functions {
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
        functions.put("echo", args -> {
            for (Value arg : args) {
                System.out.println(arg);
            }
            return NumberValue.ZERO;
        });

        functions.put("newarray", args -> {
            return new ArrayValue(args);
        });

        functions.put("newarray", new Function() {
            @Override
            public Value execute(Value... args) {
                return createArray(args, 0);
            }

            private ArrayValue createArray(Value[] args, int index) {
                final int size = (int) args[index].asNumber();
                final int last = args.length - 1;
                ArrayValue array = new ArrayValue(size);

                if (index == last) {
                    for (int i = 0; i < size; i++) {
                        array.set(i, NumberValue.ZERO);
                    }
                } else if (index < last) {
                    for (int i = 0; i < size; i++) {
                        array.set(i, createArray(args, index + 1));
                    }
                }
                return array;
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
