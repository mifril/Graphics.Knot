package ru.nsu.vakhrushev.knot.controller;

import ru.nsu.vakhrushev.knot.model.Model;
import ru.nsu.vakhrushev.knot.model.PaintType;

import java.awt.image.BufferedImage;

/**
 * @author Maxim Vakhrushev
 */
public class ApplicationController {

    private Model model;
    private PaintController paintController;
    private PaintType paintType = PaintType.DEFAULT_KNOT;

    public ApplicationController(Model model) {
        this.model = model;
        paintController = new PaintController(this.model);
    }

    public void paint(int width, int height) {
        model.setNewImageSize(width, height);
        paintController.paintKnot(paintType);
    }

    public BufferedImage getImage() {
        return model.getImage();
    }

    public void setPaintType(PaintType paintType) {
        this.paintType = paintType;
    }

    public void zoom(int zoomFactor) {
        model.setBoxCenterDistance(model.getBoxCenterDistance() + zoomFactor);
    }

    public void verticalDrag(int dragFactor) {
        model.setVerticalSeeAngle((model.getVerticalSeeAngle() + 2*Math.signum(dragFactor)) % 360);
    }

    public void horizontalDrag(int dragFactor) {
        model.setHorizontalSeeAngle((model.getHorizontalSeeAngle() + 2*Math.signum(dragFactor)) % 360);
    }

    public void setSplinesNumber(int splinesNumber) {
        model.setSplinesNumber(splinesNumber);
    }
}
