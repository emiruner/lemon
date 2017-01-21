package lemon.prims;

import lemon.core.Environment;
import lemon.core.LObject;
import lemon.core.Pair;
import lemon.core.Subr;

public class Car extends Subr {
    public Car() {
        super("car");
    }

    @Override
    public LObject call(LObject args, Environment env) {
        return Pair.first(Pair.first(args));
    }
}
