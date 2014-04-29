package ru.nsu.vakhrushev.knot.view;

import ru.nsu.vakhrushev.knot.controller.ApplicationController;
import ru.nsu.vakhrushev.knot.model.PaintType;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Maxim Vakhrushev
 */
public class InterfacePanel extends JPanel {
    public InterfacePanel (final ApplicationController applicationController, final MainFrame mainFrame) {
        JRadioButton defaultKnotButton = new JRadioButton("Default knot");
        JRadioButton myKnotButton = new JRadioButton("My knot");
        defaultKnotButton.setSelected(true);
        ButtonGroup group = new ButtonGroup();
        group.add(defaultKnotButton);
        group.add(myKnotButton);

        defaultKnotButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applicationController.setPaintType(PaintType.DEFAULT_KNOT);
                mainFrame.repaint();
            }
        });
        myKnotButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applicationController.setPaintType(PaintType.MY_KNOT);
                mainFrame.repaint();
            }
        });

        JLabel splinesNumberLabel = new JLabel("Splines number: ");

        SpinnerNumberModel spinnerNumberModel = new SpinnerNumberModel(6, 4, 20, 1);
        final JSpinner pointsNumberSpinner = new JSpinner(spinnerNumberModel);
        JFormattedTextField tf = ((JSpinner.DefaultEditor)pointsNumberSpinner.getEditor()).getTextField();
        tf.setColumns(4);
        pointsNumberSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                applicationController.setSplinesNumber((Integer)pointsNumberSpinner.getValue());
                mainFrame.repaint();
            }
        });

        add(defaultKnotButton);
        add(myKnotButton);
        add(splinesNumberLabel);
        add(pointsNumberSpinner);
    }
}
