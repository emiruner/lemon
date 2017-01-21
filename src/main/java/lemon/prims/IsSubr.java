package lemon.prims;

import lemon.core.*;

public class IsSubr extends Subr {
    public IsSubr() {
        super("subr?");
    }

    @Override
    public LObject call(LObject args, Environment env) {
        ensureParameterCountIs(args, 1);
        return Pair.first(args) instanceof Subr ? Constants.T : Constants.F;
    }
}
