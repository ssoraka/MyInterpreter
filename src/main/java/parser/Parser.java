package parser;

import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.HeaderTokenizer;
import parser.ast.BinaryExpression;
import parser.ast.Expression;
import parser.ast.NumberExpression;
import parser.ast.UnaryExpression;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    final static Token EOF = new Token(TokenType.EOF, "");

    final private List<Token> tokens;

    private int pos;
    final private int size;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        size = tokens.size();
    }

    public List<Expression> parse(){
        List<Expression> result = new ArrayList<>();

        while (!match(TokenType.EOF)) {
            result.add(expression());
        }
        return result;
    }




    private Expression expression() {
        return additive();
    }

    private Expression additive() {
        Expression result = multiplicative();

        while (true) {
            if (match(TokenType.PLUS)){
                result = new BinaryExpression('+', result, multiplicative());
                continue;
            }
            if (match(TokenType.MINUS)){
                result = new BinaryExpression('-', result, multiplicative());
                continue;
            }
            break;
        }
        return result;
    }

    private Expression multiplicative() {
        Expression result = unary();

        while (true) {
            if (match(TokenType.STAR)){
                result = new BinaryExpression('*', result, unary());
                continue;
            }
            if (match(TokenType.SLASH)){
                result = new BinaryExpression('/', result, unary());
                continue;
            }
            break;
        }
        return result;
    }

    private Expression unary() {
        if (match(TokenType.MINUS)){
            return new UnaryExpression('-', primary());
        }
        return primary();
    }

    private Expression primary() {
        final Token current = get(0);

        if (match(TokenType.NUMBER)) {
            return new NumberExpression(Double.parseDouble(current.getText()));
        }
        throw new RuntimeException("Unknown Expression");
    }

    private boolean match(TokenType type) {
        final Token current = get(0);
        if (type != current.getType())
            return false;
        pos++;
        return true;
    }

    private Token get(int relativePosition) {
        final int position = pos + relativePosition;

        if (position >= size) return EOF;
        return tokens.get(position);
    }
}
