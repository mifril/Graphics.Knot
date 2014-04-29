package ru.nsu.vakhrushev.knot.model.curve;

import ru.nsu.vakhrushev.knot.model.Point3D;

/**
 * @author Maxim Vakhrushev
 */
public interface Curve {
    public double getX(double t);
    public double getY(double t);
    public double getZ(double t);
    public Point3D getValue(double t);
}
