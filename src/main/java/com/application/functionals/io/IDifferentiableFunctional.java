package com.application.functionals.io;

import com.application.functions.io.IFunction;
import com.application.models.vectors.IVector;

public interface IDifferentiableFunctional extends IFunctional {
    IVector gradient(IFunction vector);
}
