package parser.ast;

import lib.Value;
import lib.Variables;

public class VariableExpression implements Expression{

    private final String name;

    public VariableExpression(String name) {
        this.name = name;
    }

    @Override
    public Value eval() {
        if (!Variables.isExists(name)) {
            throw new RuntimeException("Constant " + name + " does not exists");
        }
        return Variables.get(name);
    }

    @Override
    public String toString() {
        return  String.format("%s [%s]", name, Variables.get(name).asString());
    }
}
