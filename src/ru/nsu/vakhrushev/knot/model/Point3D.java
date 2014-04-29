package ru.nsu.vakhrushev.knot.model;

/**
 * @author Maxim Vakhrushev
 */
public class Point3D {
    private double x;
    private double y;
    private double z;

    public Point3D() {
    }

    public Point3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point3D(Point3D other) {
        this.x = other.x;
        this.y = other.y;
        this.z = other.z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setZ(double z) {
        this.z = z;
    }
}
