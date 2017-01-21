package lemon.prims;

import lemon.core.Environment;
import lemon.core.LObject;
import lemon.core.Pair;
import lemon.core.Subr;

public class AutoquoteEvaluator extends Subr {
    public AutoquoteEvaluator() {
        super("autoquote evaluator");
    }

    @Override
    public LObject call(LObject args, Environment env) {
        return Pair.first(args);
    }
}
