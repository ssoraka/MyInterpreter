package parser;

public enum TokenType {
    NUMBER,
    HEX_NUMBER,
    WORD,
    TEXT,

    //string
    PRINT,

    PLUS,
    MINUS,
    STAR,
    SLASH,
    EQ,

    LPAREN, //(
    RPAREN, //)

    EOF
}
