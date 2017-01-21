package lemon;

import lemon.core.Environment;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        Repl repl = new Repl(EnvUtil.basicEnv(new Environment()), System.in);

        for(String arg : args) {
            repl.loadFromStream(new BufferedReader(new InputStreamReader(new FileInputStream(arg))));
        }

        repl.loop();
    }
}
