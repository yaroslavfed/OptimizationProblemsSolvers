package com.application.optimizationproblems.functionals;

import com.application.models.functionals.LOneNormImpl;
import com.application.models.functions.LinearFunctionImpl;
import com.application.models.functions.io.DifferentiableFunction;
import com.application.models.vectors.VectorImpl;
import com.application.models.vectors.io.Vector;
import org.junit.jupiter.api.Test;

import java.lang.instrument.IllegalClassFormatException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LOneNormTests {
    @Test
    void L1Norm_Value_ReturnsCorrectValue() {
        // Arrange
        List<Vector> points = Arrays.asList(new VectorImpl(List.of(1.)), new VectorImpl(List.of(2.)), new VectorImpl(List.of(3.)));
        var values = new VectorImpl(Arrays.asList(2., 4., 6.));
        var l1Norm = new LOneNormImpl(points, values);
        var linearFunction = new LinearFunctionImpl();
        var parameters = new VectorImpl(Arrays.asList(0., 2.));
        var function = linearFunction.bind(parameters);

        // Act
        double result = l1Norm.value(function);

        // Assert
        assertEquals(0, result);
    }

    @Test
    void L1Norm_Value_ThrowsIllegalArgumentException_IfPointAndValueCountNotMatch() {
        // Arrange
        List<Vector> points = List.of(new VectorImpl(1), new VectorImpl(2));
        VectorImpl values = new VectorImpl(Arrays.asList(2., 4., 6.));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new LOneNormImpl(points, values));
    }

    @Test
    void L1Norm_Gradient_ReturnsCorrectGradient() throws IllegalClassFormatException {
        // Arrange
        List<Vector> points = Arrays.asList(new VectorImpl(List.of(1.)), new VectorImpl(List.of(2.)), new VectorImpl(List.of(3.)));
        var values = new VectorImpl(Arrays.asList(2., 4., 6.));
        var l1Norm = new LOneNormImpl(points, values);
        var linearFunction = new LinearFunctionImpl();
        var parameters = new VectorImpl(Arrays.asList(0., 2.));
        var function = (DifferentiableFunction) linearFunction.bind(parameters);

        // Act
        var gradient = l1Norm.gradient(function);

        // Assert
        assertEquals(0, gradient.getFirst(), 1e-5);
    }
}
