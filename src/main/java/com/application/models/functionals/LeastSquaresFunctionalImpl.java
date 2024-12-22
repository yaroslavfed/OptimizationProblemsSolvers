package com.application.models.functionals;

import com.application.data.Point;
import com.application.models.functionals.io.LeastSquaresFunctional;
import com.application.models.functions.io.Function;
import com.application.models.matrices.io.Matrix;
import com.application.models.vectors.io.Vector;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component("leastSquaresFunctional")
public class LeastSquaresFunctionalImpl implements LeastSquaresFunctional {
    @Override
    public Vector residual(Function function) {
        return null;
    }

    @Override
    public Matrix jacobian(Function function) {
        return null;
    }

    @Override
    public ArrayList<Point> getPoints() {
        return null;
    }

    @Override
    public void setPoints(ArrayList<Point> points) {

    }

    @Override
    public double value(Function function) {
        return 0;
    }
}
