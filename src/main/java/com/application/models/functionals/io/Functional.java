package com.application.models.functionals.io;

import com.application.models.functions.io.Function;
import com.application.dto.Point;

import java.util.ArrayList;

public interface Functional {
    ArrayList<Point> getPoints();

    void setPoints(ArrayList<Point> points);

    double value(Function function);
}