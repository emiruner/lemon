package lemon.prims;

import lemon.core.*;

public class Subtract extends Subr {
    public Subtract() {
        super("-");
    }

    @Override
    public LObject call(LObject args, Environment env) {
        ensureParameterTypes(args, LLong.class, LLong.class);
        return new LLong(((LLong) Pair.first(args)).getValue() - ((LLong) Pair.second(args)).getValue());
    }
}
