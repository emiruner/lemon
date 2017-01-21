package lemon.core;

public class Expr extends LObjectBase {
    public static int TYPE = 7;

    public Expr(LObject formals, LObject body, Environment env) {
        super(TYPE, 3);

        atPut(0, formals);
        atPut(1, body);
        atPut(2, env);
    }

    public LObject getFormals() {
        return at(0);
    }

    public LObject getBody() {
        return at(1);
    }

    public Environment getEnv() {
        return (Environment) at(2);
    }

    @Override
    public String toString() {
        return "expr " + getFormals() + " { " + getBody() + " }";
    }

    @Override
    public int getType() {
        return TYPE;
    }
}
