package lemon.core;

public class LString implements LObject {
    public static int TYPE = 4;
    private final String value;

    public LString(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return '\"' + value + '\"';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LString lString = (LString) o;

        return value.equals(lString.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public int getType() {
        return TYPE;
    }

    public String getValue() {
        return value;
    }
}
