package com.application.models.vectors;

import com.application.models.vectors.io.Vector;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class VectorImpl extends ArrayList<Double> implements Vector {
    public VectorImpl() {
        super();
    }

    public VectorImpl(int initialCapacity) {
        super(initialCapacity);

        IntStream.range(0, initialCapacity).mapToObj(_ -> 0.0).forEach(this::add);
    }

    public VectorImpl(List<Double> baseVector) {
        super(baseVector);
    }

    @Override
    public String toString() {
        return this.stream()
                .map(item -> "|" + item + "|\n")
                .collect(Collectors.joining());
    }
}
