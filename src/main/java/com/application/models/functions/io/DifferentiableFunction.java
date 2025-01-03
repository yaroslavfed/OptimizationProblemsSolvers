package com.application.models.functions.io;

import com.application.models.vectors.io.Vector;

public interface DifferentiableFunction extends Function {
    Vector gradient(Vector point) throws IllegalArgumentException;
}
