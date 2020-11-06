package parser;

import com.sun.tools.internal.ws.wsdl.parser.HTTPExtensionHandler;

import java.util.ArrayList;
import java.util.List;

public class Lexer {

    static final TokenType[] OPERATION_TOKEN = {TokenType.PLUS, TokenType.MINUS, TokenType.STAR, TokenType.SLASH};
    static final String OPERATION_CHARS = "+-*/";
    private final String input;
    private final int length;

    private List<Token> tokens;

    private int pos;

    public Lexer(String input) {
        this.input = input;
        this.length = input.length();

        tokens = new ArrayList<>();
    }

    public List<Token> tokenize() {
        while (pos < length) {
            char current = peek(0);
            if (Character.isDigit(current)) {
                tokenizeNumber();
            } else if (OPERATION_CHARS.indexOf(current) != -1) {
                tokenizeOperator();
            } else {
                next();
            }
        }
        return tokens;
    }

    private void tokenizeOperator() {
        int position = OPERATION_CHARS.indexOf(peek(0));
        addToken(OPERATION_TOKEN[position]);
        next();
    }

    private void tokenizeNumber() {
        final StringBuilder buffer = new StringBuilder();
        char current = peek(0);

        while (Character.isDigit(current)) {
            buffer.append(current);
            current = next();
        }
        addToken(TokenType.NUMBER, buffer.toString());
    }

    private char next() {
        pos++;
        return peek(0);
    }

    private char peek(int relativePosition) {
        final int position = pos + relativePosition;

        if (position >= length) return '\0';
        return input.charAt(position);
    }

    private void addToken(TokenType type) {
        tokens.add(new Token(type, ""));
    }

    private void addToken(TokenType type, String text) {
        tokens.add(new Token(type, text));
    }
}
