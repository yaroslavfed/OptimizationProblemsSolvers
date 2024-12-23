package com.application.optimizationproblems.functionals;

import com.application.models.functionals.LTwoNormImpl;
import com.application.models.functions.LinearFunctionImpl;
import com.application.models.matrices.io.Matrix;
import com.application.models.vectors.VectorImpl;
import com.application.models.vectors.io.Vector;
import org.junit.jupiter.api.Test;

import java.lang.instrument.IllegalClassFormatException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LTwoNormTests {
    @Test
    public void L2Norm_Value_ReturnsCorrectValue() {
        // Arrange
        List<Vector> points = Arrays.asList(new VectorImpl(List.of(1.)), new VectorImpl(List.of(2.)), new VectorImpl(List.of(3.)));
        var values = new VectorImpl(Arrays.asList(2., 4., 6.));
        var l2Norm = new LTwoNormImpl(points, values);
        var linearFunction = new LinearFunctionImpl();
        var parameters = new VectorImpl(Arrays.asList(0., 2.));
        var function = linearFunction.bind(parameters);

        // Act
        double result = l2Norm.value(function);

        // Assert
        assertEquals(0, result, 1e-5);
    }

    @Test
    public void L2Norm_Value_ThrowsArgumentException_IfPointAndValueCountNotMatch() {
        // Arrange
        List<Vector> points = Arrays.asList(new VectorImpl(List.of(1.)), new VectorImpl(List.of(2.)));
        var values = new VectorImpl(Arrays.asList(2., 4., 6.));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new LTwoNormImpl(points, values));
    }

    @Test
    public void L2Norm_Gradient_ReturnsCorrectGradient() throws IllegalClassFormatException {
        // Arrange
        List<Vector> points = Arrays.asList(new VectorImpl(List.of(1.)), new VectorImpl(List.of(2.)), new VectorImpl(List.of(3.)));
        var values = new VectorImpl(Arrays.asList(2., 4., 6.));
        var l2Norm = new LTwoNormImpl(points, values);
        var linearFunction = new LinearFunctionImpl();
        var parameters = new VectorImpl(Arrays.asList(0., 2.));
        var function = linearFunction.bind(parameters);

        // Act
        var gradient = l2Norm.gradient(function);

        // Assert
        assertEquals(0, gradient.getFirst(), 1e-5);
    }

    @Test
    public void L2Norm_Jacobian_ReturnsCorrectJacobian() throws IllegalClassFormatException {
        // Arrange
        List<Vector> points = Arrays.asList(new VectorImpl(List.of(1.)), new VectorImpl(List.of(2.)), new VectorImpl(List.of(3.)));
        var values = new VectorImpl(Arrays.asList(2., 4., 6.));
        var l2Norm = new LTwoNormImpl(points, values);
        var linearFunction = new LinearFunctionImpl();
        var parameters = new VectorImpl(Arrays.asList(0., 2.));
        var function = linearFunction.bind(parameters);

        // Act
        Matrix jacobian = l2Norm.jacobian(function);

        // Assert
        assertEquals(3, jacobian.size());
        assertEquals(1, jacobian.get(0).get(0));
        assertEquals(1, jacobian.get(0).get(1));
        assertEquals(1, jacobian.get(1).get(0));
        assertEquals(2, jacobian.get(1).get(1));
        assertEquals(1, jacobian.get(2).get(0));
        assertEquals(3, jacobian.get(2).get(1));
    }

    @Test
    public void L2Norm_Residual_ReturnsCorrectResidual() {
        // Arrange
        List<Vector> points = Arrays.asList(new VectorImpl(List.of(1.)), new VectorImpl(List.of(2.)), new VectorImpl(List.of(3.)));
        var values = new VectorImpl(Arrays.asList(3., 5., 7.));
        var l2Norm = new LTwoNormImpl(points, values);
        var linearFunction = new LinearFunctionImpl();
        var parameters = new VectorImpl(Arrays.asList(0., 2.));
        var function = linearFunction.bind(parameters);

        // Act
        var residual = l2Norm.residual(function);

        // Assert
        assertEquals(3, residual.size());
        assertEquals(1, residual.get(0), 1e-5);
        assertEquals(1, residual.get(1), 1e-5);
        assertEquals(1, residual.get(2), 1e-5);
    }
}
