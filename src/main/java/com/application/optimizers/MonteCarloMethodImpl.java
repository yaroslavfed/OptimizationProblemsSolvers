package com.application.optimizers;

import com.application.optimizers.io.Optimizer;
import com.application.functionals.io.Functional;
import com.application.functions.io.ParametricFunction;
import com.application.models.vectors.io.Vector;
import com.application.models.vectors.VectorImpl;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class MonteCarloMethodImpl implements Optimizer {
    private int maxIters = 1000;

    @Override
    public int GetMaxIterations() {
        return maxIters;
    }

    @Override
    public void SetMaxIterations(int maxIterations) {
        maxIters = maxIterations;
    }

    @Override
    public Vector minimize(Functional objective, ParametricFunction function, Vector initialParameters) {
        return minimize(objective, function, initialParameters, new VectorImpl(), new VectorImpl());
    }

    @Override
    public Vector minimize(Functional objective, ParametricFunction function, Vector initialParameters, Vector minimumParameters) {
        return minimize(objective, function, initialParameters, minimumParameters, new VectorImpl());
    }

    @Override
    public Vector minimize(@NotNull Functional objective,
                           @NotNull ParametricFunction function,
                           Vector initialParameters,
                           Vector minimumParameters,
                           Vector maximumParameters) {
        var param = new VectorImpl();
        var minParam = new VectorImpl();

        param.addAll(initialParameters);
        minParam.addAll(initialParameters);

        var fun = function.bind(param);
        var currentMin = objective.value(fun);

        var rand = new Random(0);
        for (int i = 0; i < maxIters; i++) {
            param.replaceAll(ignored -> rand.nextDouble());

            var f = objective.value(function.bind(param));
            if (f < currentMin) {
                currentMin = f;
                for (int j = 0; j < param.size(); j++)
                    minParam.set(j, param.get(j));
            }
        }
        return minParam;
    }
}
