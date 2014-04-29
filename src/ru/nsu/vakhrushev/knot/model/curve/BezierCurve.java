package ru.nsu.vakhrushev.knot.model.curve;

import ru.nsu.vakhrushev.knot.model.Point3D;

/**
 * @author Maxim Vakhrushev
 */
public class BezierCurve implements Curve {

    private Point3D[] points;

    public BezierCurve(Point3D[] points) {
        assert (points.length == 4);
        this.points = new Point3D[4];
        System.arraycopy(points, 0, this.points, 0, points.length);
    }

    @Override
    public double getX(double t) {
        return  Math.pow((1 - t), 3) * points[0].getX() +
                3 * t * Math.pow((1 - t), 2) * points[1].getX() +
                3 * Math.pow(t, 2) * (1 - t) * points[2].getX() +
                Math.pow(t, 3) * points[3].getX();
    }

    @Override
    public double getY(double t) {
        return  Math.pow((1 - t), 3) * points[0].getY() +
                3 * t * Math.pow((1 - t), 2) * points[1].getY() +
                3 * Math.pow(t, 2) * (1 - t) * points[2].getY() +
                Math.pow(t, 3) * points[3].getY();
    }

    @Override
    public double getZ(double t) {
        return  Math.pow((1 - t), 3) * points[0].getZ() +
                3 * t * Math.pow((1 - t), 2) * points[1].getZ() +
                3 * Math.pow(t, 2) * (1 - t) * points[2].getZ() +
                Math.pow(t, 3) * points[3].getZ();
    }

    @Override
    public Point3D getValue(double t) {
        return new Point3D(getX(t), getY(t), getZ(t));
    }
}
