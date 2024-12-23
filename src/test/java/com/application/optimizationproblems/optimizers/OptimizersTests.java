package com.application.optimizationproblems.optimizers;

import com.application.models.functionals.LTwoNormImpl;
import com.application.models.functions.LinearFunctionImpl;
import com.application.models.optimizers.ConjugateGradientMethodImpl;
import com.application.models.optimizers.GaussNewtonAlgorithmImpl;
import com.application.models.vectors.VectorImpl;
import com.application.models.vectors.io.Vector;
import org.junit.jupiter.api.Test;

import java.lang.instrument.IllegalClassFormatException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OptimizersTests {
    @Test
    public void ConjugateGradient_Minimize_ReturnsCorrectParameters() throws IllegalClassFormatException {
        // Arrange
        List<Vector> points = Arrays.asList(new VectorImpl(List.of(1.)), new VectorImpl(List.of(2.)), new VectorImpl(List.of(3.)));
        var values = new VectorImpl(Arrays.asList(2., 4., 6.));
        var l2Norm = new LTwoNormImpl(points, values);
        var linearFunction = new LinearFunctionImpl();
        var initialParameters = new VectorImpl(Arrays.asList(0., 0.));
        var optimizer = new ConjugateGradientMethodImpl<>(0.001, 1000);

        // Act
        var result = optimizer.minimize(l2Norm, linearFunction, initialParameters);

        // Assert
        assertEquals(0, result.get(0), 1e-1);
        assertEquals(2, result.get(1), 1e-1);
    }

    @Test
    public void GaussNewton_Minimize_ReturnsCorrectParameters() throws IllegalClassFormatException {
        // Arrange
        List<Vector> points = Arrays.asList(new VectorImpl(List.of(1.)), new VectorImpl(List.of(2.)), new VectorImpl(List.of(3.)));
        var values = new VectorImpl(Arrays.asList(3., 5., 7.));
        var l2Norm = new LTwoNormImpl(points, values);
        var linearFunction = new LinearFunctionImpl();
        var initialParameters = new VectorImpl(Arrays.asList(0., 0.));
        var optimizer = new GaussNewtonAlgorithmImpl<>(0.001, 1000);

        // Act
        var result = optimizer.minimize(l2Norm, linearFunction, initialParameters);

        // Assert
        assertEquals(1, result.get(0), 1e-1);
        assertEquals(2, result.get(1), 1e-1);
    }
}
