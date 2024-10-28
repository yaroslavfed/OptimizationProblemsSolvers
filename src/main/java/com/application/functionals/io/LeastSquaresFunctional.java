package com.application.functionals.io;

import com.application.models.matrices.io.Matrix;
import com.application.functions.io.Function;
import com.application.models.vectors.io.Vector;

public interface LeastSquaresFunctional extends Functional {
    Vector residual(Function function);
    Matrix jacobian(Function function);
}
