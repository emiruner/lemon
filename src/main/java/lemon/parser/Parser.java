package lemon.parser;

import lemon.core.*;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Objects;

public class Parser {
    private Lexer lexer;

    public Parser() {
    }

    private static boolean isOpeningParen(String ch) {
        return Objects.equals(ch, "[") || Objects.equals(ch, "{") || Objects.equals(ch, "(");
    }

    private static boolean isClosingParen(String ch) {
        return Objects.equals(ch, "]") || Objects.equals(ch, "}") || Objects.equals(ch, ")");
    }

    public LObject parse(Reader reader) {
        this.lexer = new Lexer(reader);
        return parse();
    }

    public LObject parse(String str) {
        this.lexer = new Lexer(new StringReader(str));
        return parse();
    }

    public LObject parse() {
        return parseFromToken(lexer.nextToken());
    }

    public LObject parseFromToken(String token) {
        if(token == null) {
            return Constants.NIL;
        }

        if(isOpeningParen(token)) {
            return parseList(token);
        }

        try {
            return new LLong(Long.parseLong(token));
        } catch(NumberFormatException ex) {
            // Not a long.
        }

        if(token.startsWith("\"")) {
            return new LString(token.substring(1, token.length() - 1));
        }

        return Symbol.intern(token);
    }

    private LObject parseList(String openingParen) {
        ArrayList<LObject> elements = new ArrayList<>();

        String token;

        while(true) {
            token = lexer.nextToken();

            if(isClosingParen(token)) {
                break;
            }

            if(token == null) {
                throw new RuntimeException("EOF found while expecting closing paren");
            }

            elements.add(parseFromToken(token));
        }

        if(openingParen.equals("(") && !token.equals(")") || openingParen.equals("[") && !token.equals("]") ||
                openingParen.equals("{") && !token.equals("}")) {
            throw new RuntimeException("opening and closing parens do not match");
        }

        return Pair.list(elements.toArray(new LObject[elements.size()]));
    }
}
