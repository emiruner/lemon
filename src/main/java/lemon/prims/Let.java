package lemon.prims;

import lemon.core.*;

public class Let extends Subr {
    public Let() {
        super("let");
    }

    @Override
    public LObject call(LObject args, Environment env) {
        Environment env2 = env.extend(Constants.NIL, Constants.NIL);
        LObject bindings = Pair.first(args);
        LObject body = Pair.rest(args);

        for(LObject binding : Pair.iterable(bindings)) {
            if(!(Pair.first(binding) instanceof Symbol)) {
                throw new RuntimeException("first parameter of let must be symbol");
            }

            LObject value = Evaluator.eval(Pair.second(binding), env2);
            env2.bind((Symbol) Pair.first(binding), value);
        }

        LObject result = Constants.NIL;

        for(LObject bodyPart : Pair.iterable(body)) {
            result = Evaluator.eval(bodyPart, env2);
        }

        return result;
    }
}
