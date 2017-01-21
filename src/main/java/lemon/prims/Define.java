package lemon.prims;

import lemon.core.*;

public class Define extends Subr {
    private final Environment globals;

    public Define(Environment globals) {
        super("define");
        this.globals = globals;
    }

    @Override
    public LObject call(LObject args, Environment env) {
        if(!(Pair.first(args) instanceof Symbol)) {
            throw new RuntimeException("first parameter of define must be symbol");
        }

        LObject value = Pair.rest(args);

        if(value.getType() != Undefined.TYPE) {
            value = Evaluator.eval(Pair.first(Pair.rest(args)), env);
        }

        globals.bind((Symbol) Pair.first(args), value);
        return value;
    }
}
