package com.application.services.optimizers;

import com.application.enums.OptimizerType;
import com.application.models.optimizers.io.Optimizer;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class OptimizersServiceImpl implements OptimizersService {
    @Override
    public Optimizer getOptimizer(ApplicationContext context, OptimizerType optimizerType) {
        var optimizersServices = context.getBeansOfType(Optimizer.class);

        return optimizersServices.get(optimizerType.toString());
    }
}
