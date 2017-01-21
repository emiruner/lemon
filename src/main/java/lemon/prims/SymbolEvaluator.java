package lemon.prims;

import lemon.core.*;

public class SymbolEvaluator extends Subr {
    public SymbolEvaluator() {
        super("symbol evaluator");
    }

    @Override
    public LObject call(LObject args, Environment env) {
        return ((Environment) Pair.second(args)).lookup((Symbol) Pair.first(args));
    }
}
