package lemon.prims;

import lemon.core.*;

public class ArrayAt extends Subr {
    public ArrayAt() {
        super("array-at");
    }

    @Override
    public LObject call(LObject args, Environment env) {
        Array array = (Array) Pair.first(args);
        LLong index = (LLong) Pair.second(args);

        return array.at((int) index.getValue());
    }
}
