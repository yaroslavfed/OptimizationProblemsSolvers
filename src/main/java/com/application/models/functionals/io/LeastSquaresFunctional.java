package com.application.models.functionals.io;

import com.application.models.matrices.io.Matrix;
import com.application.models.functions.io.Function;
import com.application.models.vectors.io.Vector;

public interface LeastSquaresFunctional extends Functional {
    Vector residual(Function function);
    Matrix jacobian(Function function);
}
