package lemon.prims;

import lemon.core.*;

public class IsEqual extends Subr {
    public IsEqual() {
        super("equal?");
    }

    @Override
    public LObject call(LObject args, Environment env) {
        ensureParameterCountIs(args, 2);
        return Pair.first(args).equals(Pair.second(args)) ? Constants.T : Constants.F;
    }
}
