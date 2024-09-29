package com.solver.optimizers.configs;

import com.solver.optimizers.abstractions.optimizators.IOptimizer;
import com.solver.optimizers.implementations.MinimizerMonteCarlo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class OptimizersConfig {
    @Bean(name = "monteCarlOptimizer")
    public IOptimizer monteCarlOptimizer() {
        return new MinimizerMonteCarlo();
    }
}