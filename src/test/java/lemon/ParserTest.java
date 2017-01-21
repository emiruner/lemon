package lemon;

import org.junit.Before;
import org.junit.Test;
import lemon.core.LLong;
import lemon.core.LObject;
import lemon.core.LString;
import lemon.core.Symbol;
import lemon.parser.Parser;

import static junit.framework.Assert.assertEquals;
import static lemon.core.Pair.list;

public class ParserTest {
    private Parser parser;

    @Before
    public void setUp() {
        parser = new Parser();
    }

    @Test
    public void testSingleNumber() {
        assertEquals(new LLong(7), parser.parse("7"));
    }

    @Test
    public void testParse() {
        final Object result = parser.parse("(\n((lambda (a) (lambda (b)\n (list a \nb))) 1) 2)");
        final Object expected = list(list(list(Symbol.intern("lambda"), list(Symbol.intern("a")), list(Symbol.intern("lambda"),
                list(Symbol.intern("b")), list(Symbol.intern("list"), Symbol.intern("a"), Symbol.intern("b")))),
                new LLong(1)), new LLong(2));

        assertEquals(expected.toString(), result.toString());
    }

    @Test
    public void emptyList() {
        assertEquals(list(), parser.parse("()"));
    }

    @Test(expected = Exception.class)
    public void testNotClosedString() {
        parser.parse("(param2 \"too bad)");
    }

    @Test
    public void alternativeBraces() {
        final Object result = parser.parse("(\n[(lambda (a) (lambda (b)\n {list a \nb})) 1] 2)");
        final Object expected = list(list(list(Symbol.intern("lambda"), list(Symbol.intern("a")), list(Symbol.intern("lambda"),
                list(Symbol.intern("b")), list(Symbol.intern("list"), Symbol.intern("a"), Symbol.intern("b")))),
                new LLong(1)), new LLong(2));

        assertEquals(expected.toString(), result.toString());
    }

    @Test
    public void stringTest() {
        final LObject result = new Parser().parse("[puts \"hello, world\"]");
        final LObject expected = list(Symbol.intern("puts"), new LString("hello, world"));

        assertEquals(expected.toString(), result.toString());
    }
}
