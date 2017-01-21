package lemon.prims;

import lemon.core.*;

import java.util.ArrayList;

public class PairEvaluator extends Subr {
    public PairEvaluator() {
        super("pair evaluator");
    }

    @Override
    public LObject call(LObject args, Environment env) {
        LObject exp = Pair.first(args);
        Environment expEnv = (Environment) Pair.second(args);

        LObject fn = Evaluator.eval(Pair.first(exp), env);

        if(fn.getType() == Fixed.TYPE) {
            return Evaluator.apply(((Fixed) fn).getFunction(), Pair.rest(exp), expEnv);
        } else {
            return Evaluator.apply(fn, evlis(Pair.rest(exp), expEnv), expEnv);
        }
    }

    private LObject evlis(LObject list, Environment env) {
        ArrayList<LObject> results = new ArrayList<LObject>();

        for (LObject item : Pair.iterable(list)) {
            results.add(Evaluator.eval(item, env));
        }

        return Pair.list(results.toArray(new LObject[results.size()]));
    }
}
