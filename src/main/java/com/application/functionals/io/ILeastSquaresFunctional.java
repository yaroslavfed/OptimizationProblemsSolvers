package com.application.functionals.io;

import com.application.models.matrices.IMatrix;
import com.application.functions.io.IFunction;
import com.application.models.vectors.IVector;

public interface ILeastSquaresFunctional extends IFunctional {
    IVector residual(IFunction function);
    IMatrix jacobian(IFunction function);
}
