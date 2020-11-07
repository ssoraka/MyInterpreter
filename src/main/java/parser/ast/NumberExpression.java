package parser.ast;

import lib.NumberValue;
import lib.Value;

public class NumberExpression implements Expression {

    private final Value value;

    public NumberExpression(double value) {
        this.value = new NumberValue(value);
    }

    @Override
    public Value eval() {
        return value;
    }

    @Override
    public String toString() {
        return value.asString();
    }
}
