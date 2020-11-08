package parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lexer {
    static final String OPERATION_CHARS = "+-*/()=<>!&|";

    final private static Map<String, TokenType> OPERATORS;
    static {
        OPERATORS = new HashMap<>();
        OPERATORS.put("(", TokenType.LPAREN);
        OPERATORS.put(")", TokenType.RPAREN);

        OPERATORS.put("+", TokenType.PLUS);
        OPERATORS.put("-", TokenType.MINUS);
        OPERATORS.put("*", TokenType.STAR);
        OPERATORS.put("/", TokenType.SLASH);

        OPERATORS.put("=", TokenType.EQ);
        OPERATORS.put("==", TokenType.EQEQ);
        OPERATORS.put("<", TokenType.LT);
        OPERATORS.put("<=", TokenType.LTEQ);
        OPERATORS.put(">", TokenType.GT);
        OPERATORS.put(">=", TokenType.GTEQ);
        OPERATORS.put("!", TokenType.EXCL);
        OPERATORS.put("!=", TokenType.EXCLEQ);
        OPERATORS.put("&", TokenType.AMP);
        OPERATORS.put("&&", TokenType.AMPAMP);
        OPERATORS.put("|", TokenType.BAR);
        OPERATORS.put("||", TokenType.BARBAR);
    }

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
            } else if (Character.isLetter(current)) {
                tokenizeWord();
            } else if (current == '#') {
                next();
                tokenizeHexNumber();
            } else if (current == '"') {
                tokenizeText();
            } else if (OPERATION_CHARS.indexOf(current) != -1) {
                tokenizeOperator();
            } else {
                next();
            }
        }
        return tokens;
    }

    private void tokenizeText() {
        next(); //skip "
        final StringBuilder buffer = new StringBuilder();
        char current = peek(0);

        while (true) {
            if (current == '\\') { //экранирование ковычки
                current = next();
                switch (current) {
                    case '"' : current = next(); buffer.append('\"'); continue;
                    case 'n' : current = next(); buffer.append('\n'); continue;
                    case 't' : current = next(); buffer.append('\t'); continue;
                    default: buffer.append('\\'); continue;
                }
            }
            if (current == '"') {
                break ;
            }
            buffer.append(current);
            current = next();
        }
        next(); //skip closing "
        addToken(TokenType.TEXT, buffer.toString());
    }

    private void tokenizeWord() {
        final StringBuilder buffer = new StringBuilder();
        char current = peek(0);

        while (true) {
            if (!Character.isLetterOrDigit(current) && current != '_' && current != '$') {
                break ;
            }
            buffer.append(current);
            current = next();
        }

        String toString = buffer.toString();
        switch (toString) {
            case "print" : addToken(TokenType.PRINT); break;
            case "if" : addToken(TokenType.IF); break;
            case "else" : addToken(TokenType.ELSE); break;
            default :
                addToken(TokenType.WORD, toString);
                break;
        }
    }

    private void tokenizeOperator() {
        char current = peek(0);
        if (current == '/') {
            if (current == '/') {
                next();
                next();
                tokenizeComment();
            } else if (current == '*'){
                next();
                next();
                tokenizeMultiLineComment();
            }
        }
    }

    private void tokenizeHexNumber() {
        final StringBuilder buffer = new StringBuilder();
        char current = peek(0);

        while (Character.isDigit(current) || isHexNumber(current)) {
            buffer.append(current);
            current = next();
        }
        addToken(TokenType.HEX_NUMBER, buffer.toString());
    }

    private boolean isHexNumber(char current) {
        return "abcdefABCDEF".indexOf(current) != -1;
    }

    private void tokenizeNumber() {
        final StringBuilder buffer = new StringBuilder();
        char current = peek(0);

        while (true) {
            if (current == '.') {
                if (buffer.indexOf(".") != -1) throw new RuntimeException("Invalid float number");
            } else if (!Character.isDigit(current)) {
                break ;
            }
            buffer.append(current);
            current = next();
        }
        addToken(TokenType.NUMBER, buffer.toString());
    }

    private void tokenizeComment() {
        char current = peek(0);
        while ("\n\r\0".indexOf(current) == -1) {
            current = next();
        }
    }

    private void tokenizeMultiLineComment() {
        char current = peek(0);
        while (true) {
            current = next();
            if (current == '*' && peek(1) == '/') {

            }

        }
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
