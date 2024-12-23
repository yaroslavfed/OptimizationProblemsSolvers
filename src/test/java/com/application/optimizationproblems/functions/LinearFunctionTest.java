package com.application.optimizationproblems.functions;

import com.application.models.functions.LinearFunctionImpl;
import com.application.models.functions.io.DifferentiableFunction;
import com.application.models.vectors.VectorImpl;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LinearFunctionTest {

    @Test
    public void LinearFunction_Bind_ReturnsCorrectFunction() {
        // Arrange
        var linearFunction = new LinearFunctionImpl();
        var parameters = new VectorImpl(Arrays.asList(1., 2., 3.));
        var point = new VectorImpl(Arrays.asList(2., 3.));

        // Act
        var function = linearFunction.bind(parameters);
        var result = function.value(point);

        // Assert
        assertEquals(14, result);
    }

    @Test
    public void LinearFunction_Bind_ThrowsArgumentException_IfNoParameters() {
        // Arrange
        var linearFunction = new LinearFunctionImpl();
        var parameters = new VectorImpl();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> linearFunction.bind(parameters));
    }

    @Test
    public void LinearFunction_Gradient_ReturnsCorrectGradient() {
        // Arrange
        var linearFunction = new LinearFunctionImpl();
        var parameters = new VectorImpl(Arrays.asList(1., 2., 3.));
        var point = new VectorImpl(Arrays.asList(2., 3.));
        var function = (DifferentiableFunction) linearFunction.bind(parameters);

        // Act
        var gradient = function.gradient(point);

        // Assert
        assertEquals(1, gradient.get(0));
        assertEquals(2, gradient.get(1));
        assertEquals(3, gradient.get(2));
    }
}
