package com.application.functions.io;

import com.application.models.vectors.io.Vector;

public interface ParametricFunction {
    Function bind(Vector parameters);
}
