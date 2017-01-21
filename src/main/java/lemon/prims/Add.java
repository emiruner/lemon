package lemon.prims;

import lemon.core.*;

public class Add extends Subr {
    public Add() {
        super("+");
    }

    @Override
    public LObject call(LObject args, Environment env) {
        long sum = 0;

        for(Object arg : Pair.iterable(args)) {
            sum += ((LLong) arg).getValue();
        }

        return new LLong(sum);
    }
}
