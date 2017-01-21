package lemon.core;

public class Undefined implements LObject {
    public static int TYPE = 0;

    @Override
    public String toString() {
        return "()";
    }

    @Override
    public int getType() {
        return TYPE;
    }
}
