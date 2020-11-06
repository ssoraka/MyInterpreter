package parser;

public class Token {
    private String text;
    private TokenType type;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public TokenType getType() {
        return type;
    }

    public void setType(TokenType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Token{" +
                "text='" + text + '\'' +
                ", type=" + type +
                '}';
    }

    public Token(TokenType type, String text) {
        this.text = text;
        this.type = type;
    }
}
