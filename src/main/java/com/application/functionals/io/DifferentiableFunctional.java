package com.application.functionals.io;

import com.application.functions.io.Function;
import com.application.models.vectors.io.Vector;

public interface DifferentiableFunctional extends Functional {
    Vector gradient(Function vector);
}
