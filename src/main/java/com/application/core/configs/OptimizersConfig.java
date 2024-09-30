package com.application.core.configs;

import com.application.optimizers.io.IOptimizer;
import com.application.optimizers.MinimizerMonteCarlo;
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