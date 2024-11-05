package com.application.services.optimizers;

import com.application.enums.OptimizerType;
import com.application.models.optimizers.io.Optimizer;
import org.springframework.context.ApplicationContext;

public interface OptimizersService {
    public Optimizer getOptimizer(ApplicationContext context, OptimizerType optimizerType);
}
