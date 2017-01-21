package lemon.prims;

import lemon.core.*;

public class NewArray extends Subr {
    public NewArray() {
        super("array");
    }

    @Override
    public LObject call(LObject args, Environment env) {
        if(Pair.size(args) == 0) {
            return new Array(0);
        } else {
            return new Array((int) ((LLong) Pair.first(args)).getValue());
        }
    }
}
