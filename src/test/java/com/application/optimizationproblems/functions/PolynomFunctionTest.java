package com.application.optimizationproblems.functions;

import com.application.models.functions.PolynomialFunctionImpl;
import com.application.models.functions.io.Function;
import com.application.models.vectors.VectorImpl;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PolynomFunctionTest {

    @Test
    public void PolynomFunction_Bind_ReturnsCorrectFunction() {
        // Arrange
        PolynomialFunctionImpl polynomFunction = new PolynomialFunctionImpl();
        VectorImpl parameters = new VectorImpl(Arrays.asList(1., 2., 3.));
        VectorImpl point = new VectorImpl(List.of(2.));

        // Act
        Function function = polynomFunction.bind(parameters);
        double result = function.value(point);

        // Assert
        assertEquals(17, result);
    }

    @Test
    public void PolynomFunction_Bind_ThrowsArgumentException_IfNoParameters() {
        // Arrange
        PolynomialFunctionImpl polynomFunction = new PolynomialFunctionImpl();
        VectorImpl parameters = new VectorImpl();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> polynomFunction.bind(parameters));
    }

    @Test
    public void PolynomFunction_InternalPolynomFunction_ThrowsArgumentException_IfPointDimensionIncorrect() {
        // Arrange
        PolynomialFunctionImpl polynomFunction = new PolynomialFunctionImpl();
        VectorImpl parameters = new VectorImpl(Arrays.asList(1., 2., 3.));
        Function function = polynomFunction.bind(parameters);
        VectorImpl point = new VectorImpl(Arrays.asList(2., 3.));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> function.value(point));
    }
}
