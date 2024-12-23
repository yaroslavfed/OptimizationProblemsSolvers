package com.application.models.functionals.io;

import com.application.models.functions.io.Function;
import com.application.models.vectors.io.Vector;

import java.lang.instrument.IllegalClassFormatException;

public interface DifferentiableFunctional extends Functional {
    Vector gradient(Function vector) throws IllegalClassFormatException;
}
