package lemon.prims;

import lemon.core.*;

public class Multiply extends Subr {
    public Multiply() {
        super("*");
    }

    @Override
    public LObject call(LObject args, Environment env) {
        long mult = 1;

        for(Object arg : Pair.iterable(args)) {
            mult *= ((LLong) arg).getValue();
        }

        return new LLong(mult);
    }
}
