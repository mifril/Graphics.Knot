package ru.nsu.vakhrushev.knot.model.knot;

/**
 * @author Maxim Vakhrushev
 */
public class TrefoilKnot implements Knot{

    private double minX = Double.MAX_VALUE;
    private double maxX = Double.MIN_VALUE;
    private double minY = Double.MAX_VALUE;
    private double maxY = Double.MIN_VALUE;
    private double minZ = Double.MAX_VALUE;
    private double maxZ = Double.MIN_VALUE;

    public TrefoilKnot() {
        findCorners();
    }

    @Override
    public double getX(double t) {
        return Math.sin(Math.toRadians(t)) + 2 * Math.sin(Math.toRadians(2 * t));
    }

    @Override
    public double getY(double t) {
        return Math.cos(Math.toRadians(t)) - 2 * Math.cos(Math.toRadians(2 * t));
    }

    @Override
    public double getZ(double t) {
        return - Math.sin(Math.toRadians(3 * t));
    }

    private void findCorners() {
        double t = 0;
        double step = 0.01;

        while (t < 360) {
            double x = getX(t);
            double y = getY(t);
            double z = getZ(t);

            if (x < minX) {
                minX = x;
            }
            if (x > maxX) {
                maxX = x;
            }

            if (y < minY) {
                minY = y;
            }
            if (y > maxY) {
                maxY = y;
            }

            if (z < minZ) {
                minZ = z;
            }
            if (z > maxZ) {
                maxZ = z;
            }
            t += step;
        }
    }

    public double getMinX() {
        return minX;
    }

    public double getMaxX() {
        return maxX;
    }

    public double getMinY() {
        return minY;
    }

    public double getMaxY() {
        return maxY;
    }

    public double getMinZ() {
        return minZ;
    }

    public double getMaxZ() {
        return maxZ;
    }
}
