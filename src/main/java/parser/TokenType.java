package parser;

public enum TokenType {
    NUMBER,
    HEX_NUMBER,
    WORD,
    TEXT,

    //string
    PRINT,
    IF,
    ELSE,
    WHILE,
    FOR,
    DO,
    BREAK,
    CONTINUE,

    PLUS,
    MINUS,
    STAR,
    SLASH,
    EQ,
    LT,//<
    GT,//>
    EQEQ,//==
    EXCL,//!
    EXCLEQ,//!=
    LTEQ,//<=
    GTEQ,//>=

    BAR,//|
    BARBAR,//||
    AMP,//&
    AMPAMP,//&&

    LPAREN,//(
    RPAREN,//)
    LBRASE,//{
    RBRASE,//}
    COMMA,//,

    EOF
}
