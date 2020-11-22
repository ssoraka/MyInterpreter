package parser.ast;

import lib.Value;

public class ArrayAccessExpression implements Expression{
    private final String variable;
    private final Expression expression;

    public ArrayAccessExpression(String variable, Expression expression) {
        this.variable = variable;
        this.expression = expression;
    }

    @Override
    public Value eval() {
        return null;
    }
}
