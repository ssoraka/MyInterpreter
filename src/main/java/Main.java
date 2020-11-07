import lib.Variables;
import parser.Lexer;
import parser.Parser;
import parser.Token;
import parser.ast.Expression;
import parser.ast.Statement;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
//        String text = "(PI + 3) * #F + 4 + 5 + 3 * 5";
        String text = new String(Files.readAllBytes(Paths.get("./target/classes/program.txt")), "UTF-8");

        List<Token> tokens = new Lexer(text).tokenize();
//        for (Token t :      tokens) {
//            System.out.println(t);
//        }

        List<Statement> statements = new Parser(tokens).parse();
//        for (Statement s : statements) {
//            System.out.println(s);
//        }
        for (Statement s : statements) {
            s.execute();
        }


    }
}
