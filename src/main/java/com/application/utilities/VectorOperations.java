package com.application.utilities;

import com.application.models.vectors.VectorImpl;
import com.application.models.vectors.io.Vector;
import org.jetbrains.annotations.NotNull;

/**
 * Математические операции над векторами
 */
public class VectorOperations {
    /**
     * Метод для вычитания векторов
     *
     * @param vector1 первый вектор
     * @param vector2 второй вектор
     * @return вектор разности
     */
    @NotNull
    public static Vector subtract(@NotNull Vector vector1, @NotNull Vector vector2) {
        if (vector1.size() != vector2.size()) {
            throw new IllegalArgumentException("Длины векторов не равны");
        }
        Vector result = new VectorImpl();
        for (int i = 0; i < vector1.size(); i++) {
            result.set(i, vector1.get(i) - vector2.get(i));
        }
        return result;
    }

    /**
     * Метод для сложения векторов
     *
     * @param vector1 первый вектор
     * @param vector2 второй вектор
     * @return вектор суммы
     */
    @NotNull
    public static Vector sum(@NotNull Vector vector1, @NotNull Vector vector2) {
        if (vector1.size() != vector2.size()) {
            throw new IllegalArgumentException("Длины векторов не равны");
        }
        Vector result = new VectorImpl();
        for (int i = 0; i < vector1.size(); i++) {
            result.set(i, vector1.get(i) + vector2.get(i));
        }
        return result;
    }

    /**
     * Метод для скалярного произведения
     *
     * @param vector1 первый вектор
     * @param vector2 второй вектор
     * @return произведение векторов
     */
    public static double multiply(@NotNull Vector vector1, @NotNull Vector vector2) {
        if (vector1.size() != vector2.size()) {
            throw new IllegalArgumentException("Длины векторов не равны");
        }
        double result = 0;
        for (int i = 0; i < vector1.size(); i++) {
            result += vector1.get(i) * vector2.get(i);
        }
        return result;
    }

    /**
     * Метод для умножения вектора на число
     *
     * @param number число на которое умножаем вектор
     * @param vector вектор который умножаем на число
     * @return вектор произведения вектора на число
     */
    @NotNull
    public static Vector multiply(double number, @NotNull Vector vector) {
        Vector result = new VectorImpl();
        for (int i = 0; i < vector.size(); i++) {
            result.set(i, number * vector.get(i));
        }
        return result;
    }
}
