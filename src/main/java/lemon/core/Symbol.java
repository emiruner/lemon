package lemon.core;

import java.util.HashMap;

public class Symbol implements LObject {
    private static HashMap<String, Symbol> interned = new HashMap<>();
    public static int TYPE = 1;

    private final LString value;

    private Symbol(String value) {
        this.value = new LString(value);
    }

    @Override
    public String toString() {
        return value.getValue();
    }

    @Override
    public boolean equals(Object o) {
        return this == o || !(o == null || getClass() != o.getClass()) && value.equals(((Symbol) o).value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public int getType() {
        return TYPE;
    }

    public static Symbol intern(String value) {
        if(!interned.containsKey(value)) {
            interned.put(value, new Symbol(value));
        }

        return interned.get(value);
    }

    public static Symbol intern(LString value) {
        return intern(value.getValue());
    }
}
