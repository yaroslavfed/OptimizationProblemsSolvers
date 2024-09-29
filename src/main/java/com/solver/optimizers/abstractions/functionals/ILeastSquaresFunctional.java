package com.solver.optimizers.abstractions.functionals;

import com.solver.optimizers.abstractions.models.IMatrix;
import com.solver.optimizers.abstractions.functions.IFunction;
import com.solver.optimizers.abstractions.models.IVector;

public interface ILeastSquaresFunctional extends IFunctional {
    IVector residual(IFunction function);
    IMatrix jacobian(IFunction function);
}
