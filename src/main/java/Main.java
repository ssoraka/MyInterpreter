import parser.Lexer;
import parser.Parser;
import parser.Token;
import parser.ast.Expression;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String text = "(2 + 3) * #F";

        List<Token> tokens = new Lexer(text).tokenize();
        for (Token t :      tokens) {
            System.out.println(t);
        }

        List<Expression> expressions = new Parser(tokens).parse();
        for (Expression e : expressions) {
            System.out.println(e + " = " + e.eval());
        }
    }
}
