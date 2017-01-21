package lemon.core;

public class LLong implements LObject {
    public static int TYPE = 5;

    private final long value;

    public LLong(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        long ovalue = ((LLong) o).getValue();

        return value == ovalue;
    }

    @Override
    public int hashCode() {
        return (int) (value ^ (value >>> 32));
    }

    @Override
    public String toString() {
        return Long.toString(value);
    }

    @Override
    public int getType() {
        return TYPE;
    }
}
