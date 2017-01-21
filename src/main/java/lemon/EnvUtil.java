package lemon;

public class EnvUtil {
    public static lemon.core.Environment basicEnv(lemon.core.Environment env) {
        bindSubr(env, new lemon.prims.Add());
        bindSubr(env, new lemon.prims.Subtract());
        bindSubr(env, new lemon.prims.Multiply());
        bindSubr(env, new lemon.prims.IsSubr());
        bindSubr(env, new lemon.prims.IsPair());
        bindSubr(env, new lemon.prims.IsEqual());
        bindSubr(env, new lemon.prims.Lt());
        bindSubr(env, new lemon.prims.NewArray());
        bindSubr(env, new lemon.prims.SetArrayAt());
        bindSubr(env, new lemon.prims.ArrayAt());
        bindSubr(env, new lemon.prims.Car());
        bindSubr(env, new lemon.prims.Cdr());

        bindFixed(env, new lemon.prims.Set());
        bindFixed(env, new lemon.prims.Let());
        bindFixed(env, new lemon.prims.Lambda());
        bindFixed(env, new lemon.prims.Quote());
        bindFixed(env, new lemon.prims.Define(env));
        bindFixed(env, new lemon.prims.If());

        lemon.core.Array evaluators = new lemon.core.Array(20);

        evaluators.atPut(lemon.core.Undefined.TYPE, new lemon.prims.AutoquoteEvaluator());
        evaluators.atPut(lemon.core.LLong.TYPE, new lemon.prims.AutoquoteEvaluator());
        evaluators.atPut(lemon.core.LString.TYPE, new lemon.prims.AutoquoteEvaluator());
        evaluators.atPut(lemon.core.Symbol.TYPE, new lemon.prims.SymbolEvaluator());
        evaluators.atPut(lemon.core.Pair.TYPE, new lemon.prims.PairEvaluator());

        lemon.core.Array applicators = new lemon.core.Array(20);

        applicators.atPut(lemon.core.Expr.TYPE, new lemon.prims.ExprApplicator());

        env.bind(lemon.core.Symbol.intern("*evaluators*"), evaluators);
        env.bind(lemon.core.Symbol.intern("*applicators*"), applicators);

        return env;
    }

    private static void bindSubr(lemon.core.Environment env, lemon.core.Subr subr) {
        env.bind(lemon.core.Symbol.intern(subr.getName()), subr);
    }

    private static void bindFixed(lemon.core.Environment env, lemon.core.Subr subr) {
        env.bind(lemon.core.Symbol.intern(subr.getName()), new lemon.core.Fixed(subr));
    }
}
