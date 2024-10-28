package com.application.optimizers;

import com.application.functionals.io.DifferentiableFunctional;
import com.application.functionals.io.Functional;
import com.application.functions.io.ParametricFunction;
import com.application.models.vectors.io.Vector;
import com.application.optimizers.io.Optimizer;

public class ConjugateGradientMethodImpl implements Optimizer {
    private final DifferentiableFunctional functional;

    public ConjugateGradientMethodImpl(DifferentiableFunctional functional) {
        this.functional = functional;
    }

    @Override
    public int GetMaxIterations() {
        return 0;
    }

    @Override
    public void SetMaxIterations(int maxIterations) {

    }

    @Override
    public Vector minimize(Functional objective, ParametricFunction function, Vector initialParameters) {
        return null;
    }

    @Override
    public Vector minimize(Functional objective, ParametricFunction function, Vector initialParameters, Vector minimumParameters) {
        return null;
    }

    @Override
    public Vector minimize(Functional objective, ParametricFunction function, Vector initialParameters, Vector minimumParameters, Vector maximumParameters) {
        return null;
    }
}
