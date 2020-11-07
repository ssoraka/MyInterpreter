package parser.ast;

import lib.NumberValue;
import lib.Value;

public class BinaryExpression implements Expression{

    private Expression expr1, expr2;
    private char operation;

    public BinaryExpression(char operation, Expression expr1, Expression expr2) {
        this.operation = operation;
        this.expr1 = expr1;
        this.expr2 = expr2;
    }


    @Override
    public Value eval() {
        final double number1 = expr1.eval().asDouble();
        final double number2 = expr1.eval().asDouble();
        switch (operation) {
            case '*' : return new NumberValue(number1 * number2);
            case '/' : return new NumberValue(number1 / number2);
            case '-' : return new NumberValue(number1 - number2);
            case '+' :
            default :
                return new NumberValue(number1 + number2);
        }
    }

    @Override
    public String toString() {
        return String.format("[%s %c %s]", expr1, operation, expr2);
    }
}
