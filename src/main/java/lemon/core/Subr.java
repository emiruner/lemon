package lemon.core;

public abstract class Subr extends LObjectBase {
    public static int TYPE = 2;

    protected Subr(String name) {
        super(TYPE, 1);
        atPut(0, new LString(name));
    }

    public abstract LObject call(LObject args, Environment env);

    public LString getName() {
        return (LString) at(0);
    }

    @Override
    public String toString() {
        return "subr(" + getName() + ")";
    }

    public void ensureParameterCountIs(LObject parameters, int expected) {
        if(Pair.size(parameters) != expected) {
            throw new RuntimeException(toString() + " -> accepts only " + expected + " parameters");
        }
    }

    public void ensureParameterTypes(LObject parameters, Class... types) {
        ensureParameterCountIs(parameters, types.length);

        int idx = 0;

        for(LObject foundObj : Pair.iterable(parameters)) {
            Class expected = types[idx];
            Class found = foundObj.getClass();

            if(!expected.isAssignableFrom(found)) {
                throw new RuntimeException(toString() + " -> expected parameter type: " + expected + ", found: " + found);
            }

            ++idx;
        }
    }

    @Override
    public int getType() {
        return TYPE;
    }
}
