package ru.nsu.vakhrushev.knot.model.knot;

/**
 * @author Maxim Vakhrushev
 */
public interface Knot {
    public double getX(double t);
    public double getY(double t);
    public double getZ(double t);

    public double getMinX();
    public double getMaxX();
    public double getMinY();
    public double getMaxY();
    public double getMinZ();
    public double getMaxZ();

}
