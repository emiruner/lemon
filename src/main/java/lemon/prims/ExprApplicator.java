package lemon.prims;

import lemon.core.*;

public class ExprApplicator extends Subr {
    public ExprApplicator() {
        super("expr applicator");
    }

    @Override
    public LObject call(LObject args, Environment env) {
        Expr fn = (Expr) Pair.first(args);
        LObject exprArgs = Pair.second(args);
        LObject result = Constants.NIL;

        if(Pair.size(fn.getFormals()) != Pair.size(exprArgs)) {
            throw new RuntimeException("parameter count does not match");
        }

        env = fn.getEnv().extend(fn.getFormals(), exprArgs);

        for(LObject bodyElement : Pair.iterable(fn.getBody())) {
            result = Evaluator.eval(bodyElement, env);
        }

        return result;
    }
}
