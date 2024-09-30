package com.application.functionals.io;

import com.application.functions.io.IFunction;
import com.application.models.Point;

import java.util.ArrayList;

public interface IFunctional {
    ArrayList<Point> getPoints();

    void setPoints(ArrayList<Point> points);

    double value(IFunction function);
}