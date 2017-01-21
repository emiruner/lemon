package lemon.parser;

import java.io.Reader;

public class Lexer {
    private ReversibleReader reader;

    public Lexer(Reader input) {
        this.reader = new ReversibleReader(input);
    }

    private static boolean isOpeningParen(char ch) {
        return ch == '[' || ch == '{' || ch == '(';
    }

    private static boolean isClosingParen(char ch) {
        return ch == ']' || ch == '}' || ch == ')';
    }

    private static boolean isSingleCharToken(char ch) {
        return ch == '\'' || ch == ',' || ch == '`' || ch == '+' || ch == '-' || ch == '/' ||
                isOpeningParen(ch) || isClosingParen(ch);
    }

    private static boolean isTokenTerminator(char ch) {
        return ch == '\"' || isSingleCharToken(ch) || Character.isWhitespace(ch);
    }

    public String nextToken() {
        Character ch = skipSpaces();

        if (ch == null) {
            return null;
        }

        if (isSingleCharToken(ch)) {
            return Character.toString(ch);
        }

        if (ch == '\"') {
            return parseStringLiteral();
        }

        if (ch == '*') {
            Character next = reader.readChar();

            if (next == null || Character.isWhitespace(next)) {
                return Character.toString(ch);
            }

            reader.unread(next);

            if (isSingleCharToken(next)) {
                return Character.toString(ch);
            }
        }

        return parseSymbol(ch);
    }

    private String parseSymbol(Character firstChar) {
        String symbol = Character.toString(firstChar);

        while(true) {
            Character ch = reader.readChar();

            if(ch == null) {
                break;
            }

            if(isTokenTerminator(ch)) {
                reader.unread(ch);
                break;
            }

            symbol += ch;
        }

        return symbol;
    }

    private String escapeChar(char escape) {
        switch (escape) {
            case 'n': return "\n";
            case 'r': return "\r";
            case '\\': return "\\";
            default: throw new RuntimeException("unsupported escape char: \\" + escape);
        }
    }

    private String parseStringLiteral() {
        String str = "\"";

        while(true) {
            Character ch = reader.readChar();

            if(ch == null) {
                break;
            }

            if(ch == '\\') {
                Character escape = reader.readChar();

                if(escape == null || Character.isWhitespace(escape)) {
                    throw new RuntimeException("a character must be present after escape character");
                }

                str += escapeChar(escape);
            } else if(ch == '\"') {
                break;
            } else {
                str += ch;
            }
        }

        str += "\"";

        return str;
    }

    private Character skipSpaces() {
        while (true) {
            Character ch = reader.readChar();

            if (ch == null) {
                return null;
            }

            if (!Character.isWhitespace(ch)) {
                return ch;
            }
        }
    }
}
