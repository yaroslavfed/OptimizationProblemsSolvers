package com.application.core.configs;

import com.application.models.matrices.MatrixImpl;
import com.application.models.matrices.io.Matrix;
import com.application.models.vectors.VectorImpl;
import com.application.models.vectors.io.Vector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class ModelsConfig {
    @Bean(name = "vector")
    public Vector vector() {
        return new VectorImpl();
    }

    @Bean(name = "matrix")
    public Matrix matrix() {
        return new MatrixImpl();
    }
}
