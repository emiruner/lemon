package lemon;

import lemon.core.Environment;
import lemon.core.Evaluator;
import lemon.core.LObject;
import lemon.parser.Parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Repl {
    public static final String DEFAULT_PROMPT = "lemon> ";
    public static final String CONTINUATION_PROMPT = "lemon>* ";

    private Environment env;
    private BufferedReader in;

    public Repl(Environment env, InputStream inputStream) {
        this.env = env;
        this.in = new BufferedReader(new InputStreamReader(inputStream));
    }

    public void loop() throws IOException {
        Parser parser = new Parser();
        String prompt = DEFAULT_PROMPT;

        while (true) {
            try {
                System.err.print(prompt);
                String line = in.readLine();

                if (line == null) {
                    break;
                }

                LObject expr = parser.parse(line);

                if(expr == null) {
                    prompt = CONTINUATION_PROMPT;
                } else {
                    prompt = DEFAULT_PROMPT;
                    System.err.println("result: " + Evaluator.eval(expr, env));
                }
            } catch (RuntimeException ex) {
                System.err.println("Error: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    public void loadFromStream(BufferedReader input) {
        Parser parser = new Parser();

        while (true) {
            try {
                String line = input.readLine();

                if (line == null) {
                    break;
                }

                LObject expr = parser.parse(line);

                if(expr != null) {
                    Evaluator.eval(expr, env);
                }
            } catch (Exception ex) {
                System.err.println("Error: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }
}
