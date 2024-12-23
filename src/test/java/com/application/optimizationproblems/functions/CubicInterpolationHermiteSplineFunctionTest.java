package com.application.optimizationproblems.functions;

import com.application.models.functions.SplineFunctionImpl;
import com.application.models.functions.io.Function;
import com.application.models.vectors.VectorImpl;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CubicInterpolationHermiteSplineFunctionTest {

    @Test
    public void CubicInterpolationHermiteSplineFunction_Bind_ReturnsCorrectFunction() {
        // Arrange
        VectorImpl mesh = new VectorImpl(Arrays.asList(0., 1., 2.));
        SplineFunctionImpl splineFunction = new SplineFunctionImpl(mesh);
        VectorImpl parameters = new VectorImpl(Arrays.asList(1., 0., 5., 12., 33., 48.));
        VectorImpl point = new VectorImpl(List.of(0.5));

        // Act
        Function function = splineFunction.bind(parameters);
        double result = function.value(point);

        // Assert
        assertEquals(1.5, result, 1e-5);
    }

    @Test
    public void CubicInterpolationHermiteSplineFunction_Bind_ThrowsArgumentException_IfNoParameters() {
        // Arrange
        VectorImpl mesh = new VectorImpl(Arrays.asList(0., 1., 2.));
        SplineFunctionImpl splineFunction = new SplineFunctionImpl(mesh);
        VectorImpl parameters = new VectorImpl();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> splineFunction.bind(parameters));
    }

    @Test
    public void CubicInterpolationHermiteSplineFunction_InternalCubicInterpolationHermiteSplineFunction_ThrowsArgumentException_IfPointDimensionIncorrect() {
        // Arrange
        VectorImpl mesh = new VectorImpl(Arrays.asList(0., 1., 2.));
        SplineFunctionImpl splineFunction = new SplineFunctionImpl(mesh);
        VectorImpl parameters = new VectorImpl(Arrays.asList(1., 2., 3., 4., 5., 6.));
        Function function = splineFunction.bind(parameters);
        VectorImpl point = new VectorImpl(Arrays.asList(0.5, 0.6));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> function.value(point));
    }
}
