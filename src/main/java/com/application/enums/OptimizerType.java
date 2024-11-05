package com.application.enums;

public enum OptimizerType {
    MONTE_CARLO_METHOD("monteCarloMethod"),
    GAUSS_NEWTON_ALGORITHM("gaussNewtonAlgorithm"),
    CONJUGATE_GRADIENT_METHOD("conjugateGradientMethod");

    private final String optimizerName;

    OptimizerType(String optimizerName) {
        this.optimizerName = optimizerName;
    }

    @Override
    public String toString() {
        return optimizerName;
    }
}