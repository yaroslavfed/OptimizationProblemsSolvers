package com.application.optimizers;

import com.application.functionals.io.Functional;
import com.application.functionals.io.LeastSquaresFunctional;
import com.application.functions.io.ParametricFunction;
import com.application.models.vectors.io.Vector;
import com.application.optimizers.io.Optimizer;

public class GaussNewtonAlgorithmImpl implements Optimizer {
    private final LeastSquaresFunctional functional;

    public GaussNewtonAlgorithmImpl(LeastSquaresFunctional functional) {
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
