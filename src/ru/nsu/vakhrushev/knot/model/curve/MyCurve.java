package ru.nsu.vakhrushev.knot.model.curve;

import ru.nsu.vakhrushev.knot.model.Point3D;

/**
 * @author Maxim Vakhrushev
 */
public class MyCurve implements Curve {

    private Point3D[] points;

     public MyCurve(Point3D[] points) {
        this.points = new Point3D[6];
        System.arraycopy(points, 0, this.points, 0, points.length);
    }

    //(1-t)^5*P0+5*t*(1-t)^4*P1+10*t^2*(1-t)^3*P2+10*t^3*(1-t)^2*P3+5*t^4+(1-t)*P4+t^5P5
    @Override
    public double getX(double t) {
       return Math.pow((1 - t), 5) * points[0].getX() +
                5 * t * Math.pow((1 - t), 4) * points[1].getX() +
                10 * Math.pow(t, 2) * Math.pow((1 - t), 3) * points[2].getX() +
                10 * Math.pow(t, 3) * Math.pow((1 - t), 2) * points[3].getX() +
                5 * Math.pow(t, 4) * (1 - t) * points[4].getX() +
                Math.pow(t, 5) * points[5].getX();
    }

    @Override
    public double getY(double t) {
        return Math.pow((1 - t), 5) * points[0].getY() +
                5 * t * Math.pow((1 - t), 4) * points[1].getY() +
                10 * Math.pow(t, 2) * Math.pow((1 - t), 3) * points[2].getY() +
                10 * Math.pow(t, 3) * Math.pow((1 - t), 2) * points[3].getY() +
                5 * Math.pow(t, 4) * (1 - t) * points[4].getY() +
                Math.pow(t, 5) * points[5].getY();
    }

    @Override
    public double getZ(double t) {
        return Math.pow((1 - t), 5) * points[0].getZ() +
                5 * t * Math.pow((1 - t), 4) * points[1].getZ() +
                10 * Math.pow(t, 2) * Math.pow((1 - t), 3) * points[2].getZ() +
                10 * Math.pow(t, 3) * Math.pow((1 - t), 2) * points[3].getZ() +
                5 * Math.pow(t, 4) * (1 - t) * points[4].getZ() +
                Math.pow(t, 5) * points[5].getZ();
    }

    @Override
    public Point3D getValue(double t) {
        return new Point3D(getX(t), getY(t), getZ(t));
    }
}
