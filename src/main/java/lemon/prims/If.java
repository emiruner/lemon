package lemon.prims;

import lemon.core.*;

public class If extends Subr {
    public If() {
        super("if");
    }

    @Override
    public LObject call(LObject args, Environment env) {
        Object condValue = Evaluator.eval(Pair.first(args), env);

        if(Constants.T.equals(condValue)) {
            return Evaluator.eval(Pair.second(args), env);
        } else {
            return Evaluator.eval(Pair.third(args), env);
        }
    }
}
