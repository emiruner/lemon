package lemon.prims;

import lemon.core.*;

public class Lt extends Subr {
    public Lt() {
        super("<");
    }

    @Override
    public LObject call(LObject args, Environment env) {
        ensureParameterTypes(args, LLong.class, LLong.class);
        return ((LLong) Pair.first(args)).getValue() < ((LLong) Pair.second(args)).getValue() ? Constants.T : Constants.F;
    }
}

