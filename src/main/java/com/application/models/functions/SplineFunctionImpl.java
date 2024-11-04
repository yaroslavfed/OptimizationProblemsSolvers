package com.application.models.functions;

import com.application.models.functions.io.Function;
import com.application.models.functions.io.ParametricFunction;
import com.application.models.vectors.io.Vector;
import org.springframework.stereotype.Component;

@Component("splineFunction")
public class SplineFunctionImpl implements ParametricFunction {
    @Override
    public Function bind(Vector parameters) {
        return null;
    }
}
