package lemon;

import lemon.EnvUtil;
import org.junit.Test;
import lemon.core.*;
import lemon.parser.Parser;

import java.io.*;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class EvalTest {
    @Test
    public void eval0() {
        Parser parser = new Parser();
        Environment env = EnvUtil.basicEnv(new Environment());

        Evaluator.eval(parser.parse("(define fact (lambda (n) (if (equal? 1 n) 1 (* n (fact (- n 1))))))"), env);

        assertEquals(new LLong(362880), Evaluator.eval(parser.parse("(fact 9)"), env));
    }

    @Test
    public void evalUpdateOuterVar() {
        Parser parser = new Parser();
        Environment env = EnvUtil.basicEnv(new Environment());

        Evaluator.eval(parser.parse("(define aa (lambda (x) (set x 5) ((lambda () (set x 9))) x))"), env);

        assertEquals(new LLong(9), Evaluator.eval(parser.parse("(aa 11)"), env));
    }

    @Test
    public void evalNilDefine() {
        Parser parser = new Parser();
        Environment env = EnvUtil.basicEnv(new Environment());

        Evaluator.eval(parser.parse("(define undef)"), env);
        assertEquals(Undefined.TYPE, env.lookup(Symbol.intern("undef")).getType());
    }

    @Test
    public void complexEval() throws IOException {
        InputStreamReader isr = new InputStreamReader(new FileInputStream("/work/Emr/documents/Gunluk/babacek.txt"));
        BufferedReader br = new BufferedReader(isr);
        StringBuilder sb = new StringBuilder();
        String line;

        while((line = br.readLine()) != null) {
            sb.append(line).append("\n");
        }

        Parser parser = new Parser();
        Environment env = EnvUtil.basicEnv(new Environment());

        Evaluator.eval(parser.parse(sb.toString()), env);
    }
}
