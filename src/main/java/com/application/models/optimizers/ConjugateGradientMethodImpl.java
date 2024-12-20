package com.application.models.optimizers;

import com.application.models.functionals.io.DifferentiableFunctional;
import com.application.models.functionals.io.Functional;
import com.application.models.functions.io.ParametricFunction;
import com.application.models.vectors.io.Vector;
import com.application.models.optimizers.io.Optimizer;
import org.springframework.stereotype.Service;

@Service("conjugateGradientMethod")
public class ConjugateGradientMethodImpl implements Optimizer {
    private final DifferentiableFunctional functional;

    public ConjugateGradientMethodImpl(DifferentiableFunctional functional) {
        this.functional = functional;
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
