package com.application.data;

/**
 * Represents a triple of values.
 *
 * @param <T> the type of the first value
 * @param <U> the type of the second value
 * @param <V> the type of the third value
 */
public record Triple<T, U, V>(T first, U second, V third) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triple<?, ?, ?> triple = (Triple<?, ?, ?>) o;
        return first.equals(triple.first) &&
                second.equals(triple.second) &&
                third.equals(triple.third);
    }

    @Override
    public String toString() {
        return "Triple{" + "first=" + first + ", second=" + second + ", third=" + third + '}';
    }
}