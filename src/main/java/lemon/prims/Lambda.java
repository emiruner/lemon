package lemon.prims;

import lemon.core.*;

public class Lambda extends Subr {
    public Lambda() {
        super("lambda");
    }

    @Override
    public LObject call(LObject args, Environment env) {
        return new Expr(Pair.first(args), Pair.rest(args), env);
    }
}
