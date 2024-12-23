package com.application.optimizationproblems.functions;

import com.application.models.functions.PiecewiseLinearFunctionImpl;
import com.application.models.functions.io.DifferentiableFunction;
import com.application.models.vectors.VectorImpl;
import com.application.models.vectors.io.Vector;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PolyLinearFunctionTest {

    @Test
    public void PolyLinearFunction_Bind_ReturnsCorrectFunction() {
        // Arrange
        Vector mesh = new VectorImpl(Arrays.asList(0., 1., 2.));
        var polyLinearFunction = new PiecewiseLinearFunctionImpl(mesh);
        var parameters = new VectorImpl(Arrays.asList(1., 2., 3.));
        var point = new VectorImpl(List.of(0.5));

        // Act
        var function = polyLinearFunction.bind(parameters);
        var result = function.value(point);

        // Assert
        assertEquals(1.5, result);
    }

    @Test
    public void PolyLinearFunction_Bind_ThrowsArgumentException_IfNoParameters() {
        // Arrange
        var mesh = new VectorImpl(Arrays.asList(0., 1., 2.));
        var polyLinearFunction = new PiecewiseLinearFunctionImpl(mesh);
        var parameters = new VectorImpl();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> polyLinearFunction.bind(parameters));
    }

    @Test
    public void PolyLinearFunction_InternalPolyLinearFunction_ThrowsArgumentException_IfPointDimensionIncorrect() {
        // Arrange
        var mesh = new VectorImpl(Arrays.asList(0., 1., 2.));
        var polyLinearFunction = new PiecewiseLinearFunctionImpl(mesh);
        var parameters = new VectorImpl(Arrays.asList(1., 2., 3.));
        var function = polyLinearFunction.bind(parameters);
        var point = new VectorImpl(Arrays.asList(0.5, 0.6));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> function.value(point));
    }

    @Test
    public void PolyLinearFunction_Gradient_ReturnsCorrectGradient() {
        // Arrange
        var mesh = new VectorImpl(Arrays.asList(0., 1., 2.));
        var polyLinearFunction = new PiecewiseLinearFunctionImpl(mesh);
        var parameters = new VectorImpl(Arrays.asList(1., 2., 3.));
        var point = new VectorImpl(List.of(0.5));
        DifferentiableFunction function = (DifferentiableFunction) polyLinearFunction.bind(parameters);

        // Act
        var gradient = function.gradient(point);

        // Assert
        assertEquals(0.5, gradient.get(0), 1e-5);
        assertEquals(0.5, gradient.get(1), 1e-5);
        assertEquals(0, gradient.get(2), 1e-5);
    }

    @Test
    public void PolyLinearFunction_Gradient_ThrowsArgumentException_IfPointDimensionIncorrect() {
        // Arrange
        var mesh = new VectorImpl(Arrays.asList(0., 1., 2.));
        var polyLinearFunction = new PiecewiseLinearFunctionImpl(mesh);
        var parameters = new VectorImpl(Arrays.asList(1., 2., 3.));
        var function = (DifferentiableFunction) polyLinearFunction.bind(parameters);
        var point = new VectorImpl(Arrays.asList(0.5, 0.6));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> function.gradient(point));
    }
}
