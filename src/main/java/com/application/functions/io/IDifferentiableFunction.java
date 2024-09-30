package com.application.functions.io;

import com.application.models.vectors.IVector;

public interface IDifferentiableFunction extends IFunction {
    IVector gradient(IVector point);
}
