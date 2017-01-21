package lemon.core;

public class Environment extends LObjectBase {
    public static int TYPE = 8;

    public Environment() {
        super(TYPE, 2);
    }

    public LObject getParent() {
        return at(0);
    }

    public LObject getBindings() {
        return at(1);
    }
    public LObject lookup(Symbol var) {
        for (LObject binding : Pair.iterable(getBindings())) {
            if (Pair.first(binding).equals(var)) {
                return Pair.second(binding);
            }
        }

        if (!(getParent() instanceof Undefined)) {
            return ((Environment) getParent()).lookup(var);
        }

        throw new RuntimeException("No binding found for symbol: " + var + " in environment!");
    }

    private boolean containsKey(Symbol key) {
        for (LObject binding : Pair.iterable(getBindings())) {
            if (Pair.first(binding).equals(key)) {
                return true;
            }
        }

        return false;
    }

    public LObject update(Symbol var, LObject value) {
        for (LObject binding : Pair.iterable(getBindings())) {
            if (Pair.first(binding).equals(var)) {
                ((Pair) ((Pair) binding).getTail()).setHead(value);
                return value;
            }
        }

        if (!(getParent() instanceof Undefined)) {
            return ((Environment) getParent()).update(var, value);
        }

        throw new RuntimeException("No binding found for symbol: " + var + " in environment!");
    }

    public void bind(Symbol var, LObject value) {
        if(getBindings() instanceof Undefined) {
            setBindings(new Pair(Pair.list(var, value)));
        } else {
            ((Pair) getBindings()).append(Pair.list(var, value));
        }
    }

    private void setBindings(Pair bindings) {
        atPut(1, bindings);
    }

    private void setParent(Environment parent) {
        atPut(0, parent);
    }

    public Environment extend(Object variables, Object values) {
        Environment env = new Environment();
        env.setParent(this);

        while (true) {
            if (variables instanceof Undefined && values instanceof Undefined) {
                break;
            }

            if (values instanceof Undefined) {
                throw new RuntimeException("missing values");
            }

            if (variables instanceof Undefined) {
                throw new RuntimeException("extra values");
            }

            env.bind((Symbol) ((Pair) variables).getHead(), ((Pair) values).getHead());

            variables = ((Pair) variables).getTail();
            values = ((Pair) values).getTail();
        }

        return env;
    }

    @Override
    public int getType() {
        return TYPE;
    }
}
