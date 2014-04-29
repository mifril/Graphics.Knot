package ru.nsu.vakhrushev.knot.controller;

import ru.nsu.vakhrushev.knot.model.Model;
import ru.nsu.vakhrushev.knot.model.PaintType;
import ru.nsu.vakhrushev.knot.model.Point3D;
import ru.nsu.vakhrushev.knot.model.Quaternion;
import ru.nsu.vakhrushev.knot.model.curve.BezierCurve;
import ru.nsu.vakhrushev.knot.model.curve.Curve;
import ru.nsu.vakhrushev.knot.model.curve.MyCurve;
import ru.nsu.vakhrushev.knot.model.knot.Knot;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author Maxim Vakhrushev
 */
public class PaintController {
    private Model model;
    private double step;
    private double scale;

    public PaintController(Model model) {
        this.model = model;
        this.step = 0.1;
        this.scale = 100;
    }

    public void paintKnot(PaintType paintType) {
        BufferedImage image = model.getImage();
        switch (paintType) {
            case DEFAULT_KNOT:
                model.setImage(paintDefaultKnot(image));
                break;
            case MY_KNOT:
                model.setImage(paintMyKnot(image));
                break;
            default:
                throw new IllegalStateException("Illegal paint type: " + paintType);
        }
        paintBox(image);
    }

    private Point3D rotatePoint(double [] coordinates, double horizontalAngle, double verticalAngle, double eyeDistance, double boxCenterDistance, double xOffset, double yOffset) {

        double x = coordinates[0];
        double y = coordinates[1];
        double z = coordinates[2];

        //horizontal rotation
        Quaternion q = new Quaternion(horizontalAngle, new double[]{0, 1, 0});
        Quaternion p = new Quaternion(0, x, y, z);
        p = q.mul(p.mul(q.inverse()));

        //vertical rotation
        p.r = 0;
        q = new Quaternion(verticalAngle, new double[]{1, 0, 0});
        p = q.mul(p.mul(q.inverse()));

        double rotatedX = p.i;
        double rotatedY = p.j;
        double rotatedZ = p.k;

        x = ((eyeDistance * rotatedX) / (boxCenterDistance + rotatedZ + eyeDistance)) * scale + xOffset;
        y = ((eyeDistance * rotatedY) / (boxCenterDistance + rotatedZ + eyeDistance)) * scale + yOffset;
        return new Point3D(x, y, rotatedZ);
    }

    private void paintBox(BufferedImage image) {
        Knot knot = model.getKnot();
        double horizontalAngle = Math.toRadians(model.getHorizontalSeeAngle());
        double verticalAngle = Math.toRadians(model.getVerticalSeeAngle());
        double eyeDistance = model.getEyeDistance();
        double boxCenterDistance = model.getBoxCenterDistance();
        int xOffset = image.getWidth() / 2;
        int yOffset = image.getHeight() / 2;

        double minX = knot.getMinX();
        double minY = knot.getMinY();
        double minZ = knot.getMinZ();
        double maxX = knot.getMaxX();
        double maxY = knot.getMaxY();
        double maxZ = knot.getMaxZ();
        Point3D start = new Point3D();
        Point3D finish = new Point3D();
        //nearest rectangle
        if (boxCenterDistance + maxZ < 0) {
            return;
        }
        if (boxCenterDistance + minZ >= 0) {

            start = rotatePoint(new double[]{minX, minY, minZ}, horizontalAngle, verticalAngle, eyeDistance, boxCenterDistance, xOffset, yOffset);
            finish = rotatePoint(new double[]{minX, maxY, minZ}, horizontalAngle, verticalAngle, eyeDistance, boxCenterDistance, xOffset, yOffset);
            paintLine(image, Color.WHITE, (int) Math.round(start.getX()), (int) Math.round(start.getY()), (int) Math.round(finish.getX()), (int) Math.round(finish.getY()));
            start = rotatePoint(new double[]{maxX, minY, minZ}, horizontalAngle, verticalAngle, eyeDistance, boxCenterDistance, xOffset, yOffset);
            finish = rotatePoint(new double[]{maxX, maxY, minZ}, horizontalAngle, verticalAngle, eyeDistance, boxCenterDistance, xOffset, yOffset);
            paintLine(image, Color.WHITE, (int) Math.round(start.getX()), (int) Math.round(start.getY()), (int) Math.round(finish.getX()), (int) Math.round(finish.getY()));
            start = rotatePoint(new double[]{minX, minY, minZ}, horizontalAngle, verticalAngle, eyeDistance, boxCenterDistance, xOffset, yOffset);
            finish = rotatePoint(new double[]{maxX, minY, minZ}, horizontalAngle, verticalAngle, eyeDistance, boxCenterDistance, xOffset, yOffset);
            paintLine(image, Color.WHITE, (int) Math.round(start.getX()), (int) Math.round(start.getY()), (int) Math.round(finish.getX()), (int) Math.round(finish.getY()));
            start = rotatePoint(new double[]{minX, maxY, minZ}, horizontalAngle, verticalAngle, eyeDistance, boxCenterDistance, xOffset, yOffset);
            finish = rotatePoint(new double[]{maxX, maxY, minZ}, horizontalAngle, verticalAngle, eyeDistance, boxCenterDistance, xOffset, yOffset);
            paintLine(image, Color.WHITE, (int) Math.round(start.getX()), (int) Math.round(start.getY()), (int) Math.round(finish.getX()), (int) Math.round(finish.getY()));
        }
        //further rectangle
        if (boxCenterDistance + maxZ >= 0) {
            start = rotatePoint(new double[]{minX, minY, maxZ}, horizontalAngle, verticalAngle, eyeDistance, boxCenterDistance, xOffset, yOffset);
            finish = rotatePoint(new double[]{minX, maxY, maxZ}, horizontalAngle, verticalAngle, eyeDistance, boxCenterDistance, xOffset, yOffset);
            paintLine(image, Color.WHITE, (int) Math.round(start.getX()), (int) Math.round(start.getY()), (int) Math.round(finish.getX()), (int) Math.round(finish.getY()));
            start = rotatePoint(new double[]{maxX, minY, maxZ}, horizontalAngle, verticalAngle, eyeDistance, boxCenterDistance, xOffset, yOffset);
            finish = rotatePoint(new double[]{maxX, maxY, maxZ}, horizontalAngle, verticalAngle, eyeDistance, boxCenterDistance, xOffset, yOffset);
            paintLine(image, Color.WHITE, (int) Math.round(start.getX()), (int) Math.round(start.getY()), (int) Math.round(finish.getX()), (int) Math.round(finish.getY()));
            start = rotatePoint(new double[]{minX, minY, maxZ}, horizontalAngle, verticalAngle, eyeDistance, boxCenterDistance, xOffset, yOffset);
            finish = rotatePoint(new double[]{maxX, minY, maxZ}, horizontalAngle, verticalAngle, eyeDistance, boxCenterDistance, xOffset, yOffset);
            paintLine(image, Color.WHITE, (int) Math.round(start.getX()), (int) Math.round(start.getY()), (int) Math.round(finish.getX()), (int) Math.round(finish.getY()));
            start = rotatePoint(new double[]{minX, maxY, maxZ}, horizontalAngle, verticalAngle, eyeDistance, boxCenterDistance, xOffset, yOffset);
            finish = rotatePoint(new double[]{maxX, maxY, maxZ}, horizontalAngle, verticalAngle, eyeDistance, boxCenterDistance, xOffset, yOffset);
            paintLine(image, Color.WHITE, (int) Math.round(start.getX()), (int) Math.round(start.getY()), (int) Math.round(finish.getX()), (int) Math.round(finish.getY()));
        }

        double z = 0.01;

        while (boxCenterDistance + minZ < 0 && minZ < maxZ) {
            minZ += z;
        }
        //side lines
        start = rotatePoint(new double[] {minX, minY, minZ}, horizontalAngle, verticalAngle, eyeDistance, boxCenterDistance, xOffset, yOffset);
        finish = rotatePoint(new double[] {minX, minY, maxZ}, horizontalAngle, verticalAngle, eyeDistance, boxCenterDistance, xOffset, yOffset);
        paintLine(image, Color.WHITE, (int)Math.round(start.getX()), (int)Math.round(start.getY()), (int)Math.round(finish.getX()), (int)Math.round(finish.getY()));
        start = rotatePoint(new double[] {minX, maxY, minZ}, horizontalAngle, verticalAngle, eyeDistance, boxCenterDistance, xOffset, yOffset);
        finish = rotatePoint(new double[] {minX, maxY, maxZ}, horizontalAngle, verticalAngle, eyeDistance, boxCenterDistance, xOffset, yOffset);
        paintLine(image, Color.WHITE, (int)Math.round(start.getX()), (int)Math.round(start.getY()), (int)Math.round(finish.getX()), (int)Math.round(finish.getY()));
        start = rotatePoint(new double[] {maxX, minY, minZ}, horizontalAngle, verticalAngle, eyeDistance, boxCenterDistance, xOffset, yOffset);
        finish = rotatePoint(new double[] {maxX, minY, maxZ}, horizontalAngle, verticalAngle, eyeDistance, boxCenterDistance, xOffset, yOffset);
        paintLine(image, Color.WHITE, (int)Math.round(start.getX()), (int)Math.round(start.getY()), (int)Math.round(finish.getX()), (int)Math.round(finish.getY()));
        start = rotatePoint(new double[] {maxX, maxY, minZ}, horizontalAngle, verticalAngle, eyeDistance, boxCenterDistance, xOffset, yOffset);
        finish = rotatePoint(new double[] {maxX, maxY, maxZ}, horizontalAngle, verticalAngle, eyeDistance, boxCenterDistance, xOffset, yOffset);
        paintLine(image, Color.WHITE, (int)Math.round(start.getX()), (int)Math.round(start.getY()), (int)Math.round(finish.getX()), (int)Math.round(finish.getY()));
    }


    private BufferedImage paintDefaultKnot(BufferedImage image) {
        Knot knot = model.getKnot();
        int splinesNumber = model.getSplinesNumber();

        Point3D[] points = new Point3D[splinesNumber * 3];
        for (int i = 0; i < points.length; ++i) {
            points[i] = new Point3D();
        }
        double eyeDistance = model.getEyeDistance();
        double boxCenterDistance = model.getBoxCenterDistance();

        double step = (360.0 / points.length);
        double t = 0.1;

        double horizontalAngle = Math.toRadians(model.getHorizontalSeeAngle());
        double verticalAngle = Math.toRadians(model.getVerticalSeeAngle());

        int index = 0;
        while ((360 - t) > 0.1) {
            points[index] = rotatePoint(new double[]{knot.getX(t),knot.getY(t),knot.getZ(t)}, horizontalAngle, verticalAngle, eyeDistance, boxCenterDistance, image.getWidth() / 2, image.getHeight() / 2);
            t += step;
            ++index;
        }
        
        Point3D lastPoint = new Point3D();
        boolean isFirstSpline = true;
        boolean hasGap = false;

        for (int i = 0; i < points.length + i / 4; i += 4) {
            t = 0;
            step = 0.01;
            Point3D[] currPoints = new Point3D[4];

            for (int j = 0; j < currPoints.length; ++j) {
                currPoints[j] = points[(i + j - i / 4) % points.length];
            }

            Curve curve = new BezierCurve(currPoints);
            double x = 0;
            double y = 0;
            double z = 0;
            hasGap = false;

            boolean isStartPoint = true;
            while (t < 1) {
                Point3D point = curve.getValue(t);
                x = point.getX();
                y = point.getY();
                z = point.getZ();
                if (isStartPoint) {
                    isStartPoint = false;
                    lastPoint = new Point3D(point);
                }

                if (boxCenterDistance - z < 0) {
                    hasGap = true;
                    t += step;
                    continue;
                }

                if (x < 0 || x > image.getWidth() - 1 || y < 0 || y > image.getHeight() - 1) {
                    hasGap = true;

                    t += step;
                    continue;
                }

                image.setRGB((int)Math.round(x), (int)Math.round(y), Color.WHITE.getRGB());

                if (!hasGap) {
                    paintLine(image, Color.WHITE, (int) Math.round(x), (int) Math.round(y), (int) Math.round(lastPoint.getX()), (int) Math.round(lastPoint.getY()));
                } else {
                    hasGap = false;
                }
                lastPoint = new Point3D(point);
                t += step;
            }
            if (!isFirstSpline && !hasGap) {
                paintLine(image, Color.WHITE, (int)Math.round(lastPoint.getX()), (int)Math.round(lastPoint.getY()), (int)(currPoints[currPoints.length - 1].getX()), (int)(currPoints[currPoints.length - 1].getY()));
            }
            if (i == 0) {
                isFirstSpline = false;
            }
        }
        return image;
    }

    private BufferedImage paintMyKnot(BufferedImage image) {
        Knot knot = model.getKnot();
        int splinesNumber = model.getSplinesNumber();
        Point3D[] points = new Point3D[splinesNumber * 5];
        for (int i = 0; i < points.length; ++i) {
            points[i] = new Point3D();
        }
        double eyeDistance = model.getEyeDistance();
        double boxCenterDistance = model.getBoxCenterDistance();

        double step = (360.0 / points.length);
        double t = 0.1;

        double horizontalAngle = Math.toRadians(model.getHorizontalSeeAngle());
        double verticalAngle = Math.toRadians(model.getVerticalSeeAngle());

        int index = 0;
        while (t < 360) {
            points[index] = rotatePoint(new double[]{knot.getX(t),knot.getY(t),knot.getZ(t)}, horizontalAngle, verticalAngle, eyeDistance, boxCenterDistance, image.getWidth() / 2, image.getHeight() / 2);
            t += step;
            ++index;
        }

        Point3D lastPoint = new Point3D();
        boolean isFirstSpline = true;
        boolean hasGap = false;

        for (int i = 0; i < points.length + i / 6; i += 6) {
            t = 0;
            step = 0.01;
            Point3D[] currPoints = new Point3D[6];

            for (int j = 0; j < currPoints.length; ++j) {
                currPoints[j] = points[(i + j - i / 6) % points.length];
            }

            Curve curve = new MyCurve(currPoints);
            double x = 0;
            double y = 0;
            double z = 0;
            hasGap = false;

            boolean isStartPoint = true;
            while (t < 1) {
                Point3D point = curve.getValue(t);
                x = point.getX();
                y = point.getY();
                z = point.getZ();
                if (isStartPoint) {
                    isStartPoint = false;
                    lastPoint = new Point3D(point);
                }

                if (boxCenterDistance - z < 0) {
                    hasGap = true;
                    t += step;
                    continue;
                }

                if (x < 0 || x > image.getWidth() - 1 || y < 0 || y > image.getHeight() - 1) {
                    hasGap = true;

                    t += step;
                    continue;
                }

                image.setRGB((int)Math.round(x), (int)Math.round(y), Color.WHITE.getRGB());

                if (!hasGap) {
                    paintLine(image, Color.WHITE, (int) Math.round(x), (int) Math.round(y), (int) Math.round(lastPoint.getX()), (int) Math.round(lastPoint.getY()));
                } else {
                    hasGap = false;
                }
                lastPoint = new Point3D(point);
                t += step;
            }
            if (!isFirstSpline && !hasGap) {
                paintLine(image, Color.WHITE, (int)Math.round(lastPoint.getX()), (int)Math.round(lastPoint.getY()), (int)(currPoints[currPoints.length - 1].getX()), (int)(currPoints[currPoints.length - 1].getY()));
            }
            if (i == 0) {
                isFirstSpline = false;
            }
        }
        return image;
    }

    private void paintLine(BufferedImage image, Color color, int startX, int startY, int endX, int endY) {
        Graphics2D g2d = (Graphics2D)image.getGraphics();
        g2d.setColor(color);
        g2d.drawLine(startX, startY, endX, endY);
    }

    private BufferedImage paintParametricKnot(BufferedImage image) {

        Knot knot = model.getKnot();
        double eyeDistance = model.getEyeDistance();
        double boxCenterDistance = model.getBoxCenterDistance();

        double t = 0;
        double x = 0;
        double y = 0;
        double horizontalAngle = Math.toRadians(model.getHorizontalSeeAngle());
        double verticalAngle = Math.toRadians(model.getVerticalSeeAngle());

        while (t < 360) {
            Point3D point = rotatePoint(new double[]{knot.getX(t),knot.getY(t),knot.getZ(t)}, horizontalAngle, verticalAngle, eyeDistance, boxCenterDistance, image.getWidth() / 2, image.getHeight() / 2);

            x = point.getX();
            y = point.getY();
            if (x < 0 || x > image.getWidth() - 1 || y < 0 || y > image.getHeight() - 1) {
                t += step;
                continue;
            }
            image.setRGB((int)Math.round(x), (int)Math.round(y), Color.WHITE.getRGB());
            t += step;
        }
        return image;
    }
}