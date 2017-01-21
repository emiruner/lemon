package lemon.core;

public class Evaluator {
    public static LObject eval(LObject exp, Environment env) {
        Array evaluators = (Array) env.lookup(Symbol.intern("*evaluators*"));
        LObject evaluator = evaluators.at(exp.getType());

        if(evaluator.getType() == Undefined.TYPE) {
            throw new RuntimeException("evaluator for type: " + exp.getType() + " not found");
        }

        return apply(evaluator, Pair.list(exp, env), env);
    }

    public static LObject apply(LObject fun, LObject args, Environment env) {
        if(fun.getType() == Subr.TYPE) {
            return ((Subr) fun).call(args, env);
        } else {
            Array applicators = (Array) env.lookup(Symbol.intern("*applicators*"));
            LObject applicator = applicators.at(fun.getType());

            if (applicator.getType() == Undefined.TYPE) {
                throw new RuntimeException("applicator for type: " + fun.getType() + " not found");
            }

            return apply(applicator, Pair.list(fun, args, env), env);
        }
    }
}
