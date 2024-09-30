package com.application.functions.io;

import com.application.models.vectors.IVector;

public interface IParametricFunction {
    IFunction bind(IVector parameters);
}
