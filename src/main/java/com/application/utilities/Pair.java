package com.application.utilities;

/**
 * Represents a pair of values.
 *
 * @param <T> the type of the first value
 * @param <U> the type of the second value
 */
public record Pair<T, U>(T first, U second) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return first.equals(pair.first) && second.equals(pair.second);
    }

    @Override
    public String toString() {
        return "Pair{" + "first=" + first + ", second=" + second + '}';
    }
}
