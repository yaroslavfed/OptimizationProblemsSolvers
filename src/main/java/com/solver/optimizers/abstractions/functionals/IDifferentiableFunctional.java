package com.solver.optimizers.abstractions.functionals;

import com.solver.optimizers.abstractions.functions.IFunction;
import com.solver.optimizers.abstractions.models.IVector;

public interface IDifferentiableFunctional extends IFunctional {
    IVector gradient(IFunction vector);
}
