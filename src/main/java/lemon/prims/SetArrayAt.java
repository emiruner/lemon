package lemon.prims;

import lemon.core.*;

public class SetArrayAt extends Subr {
    public SetArrayAt() {
        super("set-array-at");
    }

    @Override
    public LObject call(LObject args, Environment env) {
        Array array = (Array) Pair.first(args);
        LLong index = (LLong) Pair.second(args);
        LObject value = Pair.third(args);

        array.atPut((int) index.getValue(), value);
        return value;
    }
}
