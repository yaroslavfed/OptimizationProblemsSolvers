package com.application.models.optimizers;

import com.application.models.functionals.io.Functional;
import com.application.models.functionals.io.LeastSquaresFunctional;
import com.application.models.functions.io.ParametricFunction;
import com.application.models.vectors.io.Vector;
import com.application.models.optimizers.io.Optimizer;
import org.springframework.stereotype.Service;

@Service("gaussNewtonAlgorithm")
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
