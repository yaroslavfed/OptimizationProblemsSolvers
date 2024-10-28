package com.application.models.vectors;

import com.application.models.vectors.io.Vector;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class VectorImpl extends ArrayList<Double> implements Vector {
    public VectorImpl() {
        super();
    }

    public VectorImpl(ArrayList<Double> baseVector) {
        super(baseVector);
    }

    @Override
    public String toString() {
        return this.stream()
                .map(item -> "|" + item + "|\n")
                .collect(Collectors.joining());
    }
}
