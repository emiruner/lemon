package lemon.core;

public class Fixed extends LObjectBase {
    public static int TYPE = 6;

    public Fixed(LObject function) {
        super(TYPE, 1);
        atPut(0, function);
    }

    public LObject getFunction() {
        return at(0);
    }

    @Override
    public String toString() {
        return "fixed(" + getFunction() + ")";
    }

    @Override
    public int getType() {
        return TYPE;
    }
}
