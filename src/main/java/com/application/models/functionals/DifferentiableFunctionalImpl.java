package com.application.models.functionals;

import com.application.data.Point;
import com.application.models.functionals.io.DifferentiableFunctional;
import com.application.models.functions.io.Function;
import com.application.models.vectors.io.Vector;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component("differentiableFunctional")
public class DifferentiableFunctionalImpl implements DifferentiableFunctional {
    @Override
    public Vector gradient(Function vector) {
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
