package lemon.prims;

import lemon.core.*;

public class IsPair extends Subr {
    public IsPair() {
        super("pair?");
    }

    @Override
    public LObject call(LObject args, Environment env) {
        ensureParameterCountIs(args, 1);
        return Pair.first(args) instanceof Pair ? Constants.T : Constants.F;
    }
}
