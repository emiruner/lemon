package lemon.prims;

import lemon.core.*;

public class Set extends Subr {
    public Set() {
        super("set");
    }

    @Override
    public LObject call(LObject args, Environment env) {
        if(!(Pair.first(args) instanceof Symbol)) {
            throw new RuntimeException("first parameter of define must be symbol");
        }

        LObject value = Evaluator.eval(Pair.second(args), env);
        env.update((Symbol) Pair.first(args), value);
        return value;
    }
}
