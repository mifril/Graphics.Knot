package ru.nsu.vakhrushev.knot.view;

import ru.nsu.vakhrushev.knot.controller.ApplicationController;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

/**
 * @author Maxim Vakhrushev
 */
public class MainFrame extends JFrame {

    public MainFrame(ApplicationController applicationController) {
        setTitle("Knot");
        JPanel imagePanel = new ImagePanel(applicationController, this);
        JPanel interfacePanel = new InterfacePanel(applicationController, this);

        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        interfacePanel.setMaximumSize(new Dimension(600, 100));

        add(imagePanel);
        add(interfacePanel);

        setPreferredSize(new Dimension(600, 600));
        setMaximumSize(new Dimension(1000, 600));
        setMinimumSize(new Dimension(500, 500));
        setSize(new Dimension(600, 600));

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}

