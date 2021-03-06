package lemon.prims;

import lemon.core.Environment;
import lemon.core.LObject;
import lemon.core.Pair;
import lemon.core.Subr;

public class Cdr extends Subr {
    public Cdr() {
        super("cdr");
    }

    @Override
    public LObject call(LObject args, Environment env) {
        return Pair.rest(Pair.first(args));
    }
}
