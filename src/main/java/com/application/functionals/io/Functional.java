package com.application.functionals.io;

import com.application.functions.io.Function;
import com.application.models.Point;

import java.util.ArrayList;

public interface Functional {
    ArrayList<Point> getPoints();

    void setPoints(ArrayList<Point> points);

    double value(Function function);
}