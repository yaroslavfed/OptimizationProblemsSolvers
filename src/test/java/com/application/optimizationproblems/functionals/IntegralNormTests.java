package com.application.optimizationproblems.functionals;

import com.application.models.functionals.IntegralNormImpl;
import com.application.models.functions.LinearFunctionImpl;
import com.application.models.vectors.VectorImpl;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IntegralNormTests {
    @Test
    public void Integral_Value_ReturnsCorrectValue() {
        // Arrange
        var a = new VectorImpl(List.of(0.));
        var b = new VectorImpl(List.of(1.));
        var integral = new IntegralNormImpl(a, b);
        var linearFunction = new LinearFunctionImpl();
        var parameters = new VectorImpl(Arrays.asList(0., 1.));
        var function = linearFunction.bind(parameters);

        // Act
        double result = integral.value(function);

        // Assert
        assertEquals(0.5, result, 1e-5);
    }

    @Test
    public void Integral_Value_ReturnsCorrectValue_2D() {
        // Arrange
        var a = new VectorImpl(Arrays.asList(0., 0.));
        var b = new VectorImpl(Arrays.asList(1., 1.));
        var integral = new IntegralNormImpl(a, b);
        var linearFunction = new LinearFunctionImpl();
        var parameters = new VectorImpl(Arrays.asList(0., 1., 1.));
        var function = linearFunction.bind(parameters);

        // Act
        double result = integral.value(function);

        // Assert
        assertEquals(1, result, 1e-5);
    }

    @Test
    public void Integral_Value_ThrowsArgumentException_IfDimensionNotMatch() {
        // Arrange
        var a = new VectorImpl(List.of(0.));
        var b = new VectorImpl(Arrays.asList(1., 2.));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new IntegralNormImpl(a, b));
    }
}
