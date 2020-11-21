package parser.ast;

import lib.Function;
import lib.Functions;
import lib.Value;

import java.util.ArrayList;
import java.util.List;

public class FunctionalExpression implements Expression{

    private final String name;
    private final List<Expression> arguments;

    public FunctionalExpression(String name) {
        this.name = name;
        arguments = new ArrayList<>();
    }

    public FunctionalExpression(String name, List<Expression> arguments) {
        this.name = name;
        this.arguments = arguments;
    }

    public void addArguments(Expression expression) {
        arguments.add(expression);
    }

    @Override
    public Value eval() {
        final int size = arguments.size();
        Value values[] = new Value[size];
        for (int i = 0; i < size; i++) {
            values[i] = arguments.get(i).eval();
        }
        return Functions.get(name).execute(values);
    }

    @Override
    public String toString() {
        return name + "(" + arguments.toString() + ")";
    }
}
