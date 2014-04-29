package ru.nsu.vakhrushev.knot.model;

import ru.nsu.vakhrushev.knot.model.knot.Knot;
import ru.nsu.vakhrushev.knot.model.knot.TrefoilKnot;

import java.awt.image.BufferedImage;

/**
 * @author Maxim Vakhrushev
 */
public class Model {
    private BufferedImage image;
    private Knot knot;
    private double eyeDistance;
    private double boxCenterDistance;
    private double verticalSeeAngle;
    private double horizontalSeeAngle;
    private int splinesNumber;

    public Model() {
        knot = new TrefoilKnot();
        eyeDistance = 20;
        boxCenterDistance = 20;
        verticalSeeAngle = 0;
        horizontalSeeAngle = 0;
        splinesNumber = 6;
    }

    public void setNewImageSize(int width, int height) {
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    public Knot getKnot() {
        return knot;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public double getEyeDistance() {
        return eyeDistance;
    }

    public void setEyeDistance(double eyeDistance) {
        this.eyeDistance = eyeDistance;
    }

    public double getBoxCenterDistance() {
        return boxCenterDistance;
    }

    public void setBoxCenterDistance(double boxCenterDistance) {
        this.boxCenterDistance = boxCenterDistance;
    }

    public double getVerticalSeeAngle() {
        return verticalSeeAngle;
    }

    public double getHorizontalSeeAngle() {
        return horizontalSeeAngle;
    }

    public void setVerticalSeeAngle(double verticalSeeAngle) {
        this.verticalSeeAngle = verticalSeeAngle;
    }

    public void setHorizontalSeeAngle(double horizontalSeeAngle) {
        this.horizontalSeeAngle = horizontalSeeAngle;
    }

    public int getSplinesNumber() {
        return splinesNumber;
    }

    public void setSplinesNumber(int splinesNumber) {
        this.splinesNumber = splinesNumber;
    }
}

