package com.application.optimizationproblems.functionals;

import com.application.models.functionals.LinfNormImpl;
import com.application.models.functions.LinearFunctionImpl;
import com.application.models.vectors.VectorImpl;
import com.application.models.vectors.io.Vector;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LinfNormTests {
    @Test
    public void Linf_Value_ReturnsCorrectValue() {
        // Arrange
        List<Vector> points = Arrays.asList(new VectorImpl(List.of(1.)), new VectorImpl(List.of(2.)), new VectorImpl(List.of(3.)));
        var values = new VectorImpl(Arrays.asList(3., 5., 7.));
        var linf = new LinfNormImpl(points, values);
        var linearFunction = new LinearFunctionImpl();
        var parameters = new VectorImpl(Arrays.asList(0., 2.));
        var function = linearFunction.bind(parameters);

        // Act
        double result = linf.value(function);

        // Assert
        assertEquals(1, result, 1e-5);
    }

    @Test
    public void Linf_Value_ThrowsArgumentException_IfPointAndValueCountNotMatch() {
        // Arrange
        List<Vector> points = List.of(new VectorImpl(1), new VectorImpl(2));
        var values = new VectorImpl(Arrays.asList(2., 4., 6.));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new LinfNormImpl(points, values));
    }
}
