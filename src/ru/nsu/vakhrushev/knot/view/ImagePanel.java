package ru.nsu.vakhrushev.knot.view;

import ru.nsu.vakhrushev.knot.controller.ApplicationController;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;

/**
 * @author Maxim Vakhrushev
 */
public class ImagePanel extends JPanel {
    private ApplicationController applicationController;
    private MainFrame mainFrame;
    private int previousX;
    private int previousY;

    public ImagePanel(final ApplicationController applicationController, final MainFrame mainFrame) {
        this.applicationController = applicationController;
        this.mainFrame = mainFrame;
        setBorder(BorderFactory.createLineBorder(Color.black));
        setBackground(Color.WHITE);
        addMouseMotionListener(new MouseInputAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                int y = e.getY();
                int x = e.getX();
                if (!(x > getWidth() || y > getHeight() || x < 0 || y < 0)) {
                    applicationController.verticalDrag(y - previousY);
                    applicationController.horizontalDrag(x - previousX);
                    previousY = y;
                    previousX = x;
                    mainFrame.repaint();
                }
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mouseClicked(e);
                previousX = e.getX();
                previousY = e.getY();
            }
        });
        addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                applicationController.zoom(e.getWheelRotation());
                mainFrame.repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.removeAll();
        applicationController.paint(this.getWidth(), this.getHeight());
        BufferedImage image = applicationController.getImage();
        g.drawImage(image, 0 , 0, null);
    }
}
